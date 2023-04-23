int hum_pin = 5;

void setup() {
  pinMode(hum_pin, OUTPUT);
}

void loop() {
  digitalWrite(hum_pin, HIGH);
  delay(3000);
  digitalWrite(hum_pin, LOW);
  delay(2000);

}

// 작동 안되서 문의중