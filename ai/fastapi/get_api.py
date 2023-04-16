from fastapi import FastAPI, HTTPException
import requests

app = FastAPI()


@app.get("/")
async def get_sensor_data(page: int = 0, page_size: int = 10):
    url = f"http://dayounghan.com/PlantSensor?page={page}&pageSize={page_size}"
    response = requests.get(url)

    if response.status_code != 200:
        raise HTTPException(status_code=response.status_code, detail="Failed to get sensor data.")

    sensor_data = response.json()

    return sensor_data
