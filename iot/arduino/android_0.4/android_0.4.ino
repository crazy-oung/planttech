#include <ArduinoJson.h>

#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>
#include <time.h>

#define STASSID ""   // WiFi SSID 입력
#define STAPSK  ""   // WiFi 비밀번호 입력



int hum_pin = D5;
int AA = D8;
int AB = D6;

String result = "";
String result2 = "";



struct tm *lc;          // 내가 원하는대로 날짜형식 작성을 위해 필요한 구조체
WiFiClient client;
HTTPClient http;
//HTTPClient http3;





void setup() {
  Serial.begin(115200);
  WiFi.mode(WIFI_STA);
  WiFi.begin(STASSID, STAPSK);
  configTime(-(3600 * 9), 0, "1.kr.pool.ntp.org"); // 9시간 시차, 서머타임 적용 X


  pinMode (hum_pin, OUTPUT);
  pinMode(AA, OUTPUT);
  pinMode(AB, OUTPUT);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("WiFi Connected! IP address: ");
  Serial.println(WiFi.localIP());

  Serial.println(F("Gather Temperature, Humidity, Concentrations Of Gases !"));
  Serial.println(F("Wait 60 Seconds...\n"));

}

String pluszero(int value) {    // localtime을 통해 생성된 구조체의 값이 10 미만이면
  String out;           // 한자리 숫자로 나온다. 예를들어 9일이면 09가 아닌
  if (value < 10) {       // 9로 나와서 한자리 숫자일 경우 앞에 0을 추가하는
    out.concat("0");        // 작업을 하는 함수이다.
    out.concat(String(value));
    return out;
  } else {
    out = String(value);
    return out;
  }
}

String calcdate(struct tm *t) {       // 현재 시간을 DATETIME 형식으로 바꾸어
  String out = String((1900 + t->tm_year)); // String 타입으로 return 한다.
  out.concat("-");
  out.concat(pluszero((t->tm_mon) + 1));
  out.concat("-");
  out.concat(pluszero(t->tm_mday));
  out.concat(" ");
  out.concat(pluszero(t->tm_hour));
  out.concat(":");
  out.concat(pluszero(t->tm_min));
  out.concat(":");
  out.concat(pluszero(t->tm_sec));
  return out;
}

void loop() {

  //delay(2000);  // 측정 간 30간의 지연을 설정한다.


  time_t now = time(nullptr);
  lc = localtime(&now);   // 현재 시간을 localtime을 통한 구조체 설정



  // 시간 영역
  Serial.print(F("기준 시간 : "));
  String date = calcdate(lc);
  Serial.println(date);
  Serial.println("");

  char userNo[] = "0";
  if ((WiFi.status() == WL_CONNECTED)) {

    Serial.print("[HTTP] 서버 연결을 시도합니다...\n");
    char httpUrl[] = "";  // 센서 제어 데이터 GET
    strcat(httpUrl, userNo);
    http.begin(client, httpUrl); // 요청을 보낼 URL 입력
    //char httpUrl3[] = "";
    //strcat(httpUrl3, userNo);
    //http3.begin(client, httpUrl3);
    http.addHeader("Content-Type", "application/json");
    //http3.addHeader("Content-Type", "application/json");
    // GET 요청을 할때 전송 방식을 정한다.

    Serial.print("[HTTP] 수집한 값의 GET/POST 요청을 시도합니다...\n");


    int httpCode = http.GET(); // 위에 작성한 URL로 GET 요청을 보낸다.

    //String POSTBODY = "";
    //StaticJsonBuffer<200> jsonBuffer3;
    //JsonObject& root3 = jsonBuffer3.createObject();
    // root3["waterPumpTf"] = 0;
    // root3["userNo"] = 0;
    // root3.printTo(POSTBODY);
    
    if (httpCode > 0) {
      // HTTP 헤더를 전송하고 그에 대한 응답을 핸들링하는 과정
      Serial.printf("[HTTP] 응답 Code : %d\n", httpCode);
      // HTTP 응답 200, 즉 정상응답이면 서버로부터 수신된 응답을 출력한다.
      if (httpCode == HTTP_CODE_OK) {
        const String& payload = http.getString();
        Serial.print("서버로부터 수신된 응답 : ");
        Serial.println(payload);
        Serial.println("");

      }

      result = http.getString();
      Serial.println(result);
      //위주소에서 가져온 값을 저장하고 시리얼모니터에 출력
      DynamicJsonBuffer jsonBuffer; 
    //json데이터를 유동적으로 다룰  수 있는 메모리 공간
      JsonObject& root = jsonBuffer.parseObject(result);
      String Humidifier_Sensor = root["humidifierTf"];
      String Water_Pump = root["waterPumpTf"];


      if(Humidifier_Sensor=="1"){  // 가습기 켜짐
        //digitalWrite(LED_Pin,1);
        Serial.println("가습기 ON");
        digitalWrite(hum_pin, HIGH);
      }
      else if(Humidifier_Sensor=="0") {  // 가습기 꺼짐
        //digitalWrite(LED_Pin,0);
        Serial.println("가습기 OFF");
        digitalWrite(hum_pin, LOW);
      }

      if(Water_Pump == "1"){ //워터펌프 켜짐
          // 물줘
        Serial.println("Water ON");
        digitalWrite(AA, HIGH);
        digitalWrite(AB, LOW);
        //delay(2000);

        
        // else if (Humi_ >= 90.0){
        //   // 물 그만줘
        //   Serial.println("Water OFF");
        //   digitalWrite(AA, LOW);
        //   digitalWrite(AB, LOW);
        //   int httpCode3 = http3.POST(POSTBODY); // 위에 작성한 URL로 POST 요청을 보낸다.
        //   if (httpCode3 > 0) {
        //   // HTTP 헤더를 전송하고 그에 대한 응답을 핸들링하는 과정
        //   Serial.printf("[HTTP3] 응답 Code : %d\n", httpCode3);

        //   // HTTP 응답 200, 즉 정상응답이면 서버로부터 수신된 응답을 출력한다.
        //   if (httpCode3 == HTTP_CODE_OK) {
        //     const String& payload = http3.getString();
        //     Serial.print("서버로부터 수신된 응답 : ");
        //     Serial.println(payload);
        //     Serial.println("");
        //   }
        //   }
        //   //Water_Pump 0 post
        // }
      }

      else if (Water_Pump == "0"){
      Serial.println("Water OFF");
      digitalWrite(AA, LOW);
      digitalWrite(AB, LOW);

      }
      else{
        Serial.println("워터 펌프 꺼짐 ");

      }
      
      

    } else {    // 에러발생시 에러내용을 출력한다.
      Serial.printf("[HTTP]초 POST 요청이 실패했습니다. 오류 : %s\n", http.errorToString(httpCode).c_str());
    }

    http.end();
    //http3.end();
  }
  delay(1000);
}