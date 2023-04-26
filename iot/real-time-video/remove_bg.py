import requests

def rm_bg() :
    response = requests.post(
        'https://api.remove.bg/v1.0/removebg',
        files={'image_file': open('today_plant.png', 'rb')},
        data={'size': 'auto'},
        headers={'X-Api-Key': 'GJ6Jc5idYdUWJL2eWvJKBeoW'},
    )
    if response.status_code == requests.codes.ok:
        with open('no-bg.png', 'wb') as out:
            out.write(response.content)
    else:
        print("Error:", response.status_code, response.text)