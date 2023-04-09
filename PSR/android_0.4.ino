#include <ArduinoJson.h>

#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>
#include <time.h>



#define SERVER_IP ""  // db서버의 주소
#define STASSID ""   // WiFi SSID 입력
#define STAPSK  ""   // WiFi 비밀번호 입력




int LED_Pin = D2;
int AA = D5;
int AB = D6;

String result = "";
String result2 = "";



struct tm *lc;          // 내가 원하는대로 날짜형식 작성을 위해 필요한 구조체
WiFiClient client;
HTTPClient http;
HTTPClient http2;





void setup() {
  Serial.begin(115200);
  WiFi.mode(WIFI_STA);
  WiFi.begin(STASSID, STAPSK);
  configTime(-(3600 * 9), 0, "1.kr.pool.ntp.org"); // 9시간 시차, 서머타임 적용 X


  pinMode (LED_Pin, OUTPUT);
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
    http2.begin(client, "");  // 
    http2.addHeader("Content-Type", "application/json");
    http.addHeader("Content-Type", "application/json");
    // GET 요청을 할때 전송 방식을 정한다.

    Serial.print("[HTTP] 수집한 값의 GET/POST 요청을 시도합니다...\n");


    int httpCode = http.GET(); // 위에 작성한 URL로 GET 요청을 보낸다.
    int httpCode2 = http2.GET(); // 위에 작성한 URL로 GET 요청을 보낸다.


    
    if (httpCode > 0 && httpCode2 > 0) {
      // HTTP 헤더를 전송하고 그에 대한 응답을 핸들링하는 과정
      Serial.printf("[HTTP] 응답 Code : %d\n", httpCode);
      Serial.printf("[HTTP2] 응답 Code : %d\n", httpCode2);
      // HTTP 응답 200, 즉 정상응답이면 서버로부터 수신된 응답을 출력한다.
      if (httpCode == httpCode2 == HTTP_CODE_OK) {
        const String& payload = http.getString();
        const String& payload2 = http.getString();
        Serial.print("서버로부터 수신된 응답 : ");
        Serial.println(payload);
        Serial.println("");
        Serial.print("서버로부터 수신된 응답 : ");
        Serial.println(payload2);
        Serial.println("");
      }

      result = http.getString();
      result2 = http2.getString();
      Serial.println(result);
      Serial.println(result2);
      //위주소에서 가져온 값을 저장하고 시리얼모니터에 출력
      DynamicJsonBuffer jsonBuffer; 
      DynamicJsonBuffer jsonBuffer2; 
    //json데이터를 유동적으로 다룰  수 있는 메모리 공간
      JsonObject& root = jsonBuffer.parseObject(result);
      String LED = root["ledTf"];
      String Water_Pump = root["waterPumpTf"];

      JsonArray &root2 = jsonBuffer2.parseArray(result2);
      JsonObject& parsed = root2[0];
      String HUMI = parsed["humi"];
      Serial.print("HUMI: "); Serial.println(HUMI);
      float Humi_ = HUMI.toFloat();  //문자열을 float로 변환


      if(LED=="1"){  // LED 켜짐
        //digitalWrite(LED_Pin,1);
        Serial.println("LED ON");
        digitalWrite (LED_Pin, HIGH);
      }
      else if(LED=="0") {  // LED 꺼짐
        //digitalWrite(LED_Pin,0);
        Serial.println("LED OFF");
        digitalWrite (LED_Pin, LOW);
      }

      if(Water_Pump == "1"){ //워터펌프 켜짐

        if(Humi_ < 90.0){  //습도 90 이하일 경우
          // 물줘
          Serial.println("Water ON");
          digitalWrite(AA, HIGH);
          digitalWrite(AB, LOW);
          //delay(2000);

        }
        else if (Humi_ >= 90.0){
          // 물 그만줘
          Serial.println("Water OFF");
          digitalWrite(AA, LOW);
          digitalWrite(AB, LOW);
          //Water_Pump 0 post
        }
      }
      else{
        Serial.println("워터 펌프 꺼짐 ");

      }
      
      

    } else {    // 에러발생시 에러내용을 출력한다.
      Serial.printf("[HTTP]초 POST 요청이 실패했습니다. 오류 : %s\n", http.errorToString(httpCode).c_str());
      Serial.printf("[HTTP]초 POST 요청이 실패했습니다. 오류 : %s\n", http2.errorToString(httpCode2).c_str());
    }

    http.end();
    http2.end();
  }
  delay(1000);
}