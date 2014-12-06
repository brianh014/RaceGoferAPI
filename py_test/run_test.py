__author__ = 'Miles'

from create_users import BASE_URL, USER_STRING, USER_PASS, USER_NUM
import requests as req

RACE_ID_TO_JOIN = "91632a50-1281-47f4-b388-f03e2205714d"
RACE_PASSWORD = "howdy"
BASE_LAT = 0
BASE_LONG = 90

def join_race():
    for i in range(USER_NUM):
        payload = {
            "raceId": RACE_ID_TO_JOIN,
            "password": "howdy",
            "userType": "Participant",
            "hidden": "false",
        }
        res = req.get(BASE_URL+"JoinRace", params=payload, auth=(USER_STRING+str(i), USER_PASS))
        print res.text


def run_race():
    for i in range(20):
        auth_tuple = (USER_STRING+str(i), USER_PASS)
        payload = {
            "raceId": RACE_ID_TO_JOIN,
            "latitude": BASE_LAT+i,
            "longitude": BASE_LONG
        }
        res = req.get(BASE_URL+"UpdatePosition", params=payload, auth=auth_tuple)
        print res.text


if __name__ == '__main__':
    run_race()