__author__ = 'Miles'

from create_users import BASE_URL, USER_STRING, USER_PASS, USER_NUM
import requests as req
import time
import threading, signal, sys, random

RACE_ID_TO_JOIN = "91632a50-1281-47f4-b388-f03e2205714d"
RACE_PASSWORD = "howdy"
BASE_LAT = 30.6014
BASE_LONG = -96.3144
DELTA = 0.0001

class CheckPoint(object):
    def __init__(self, lat, longi):
        self.latitude = lat
        self.longitude = longi


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


def race_thread(user_tuple, start_tuple, checkpoints):
    current_checkpoint = 0
    current_coord = start_tuple
    while True:
        if current_coord.latitude > checkpoints[current_checkpoint].latitude:
            current_coord.latitude -= DELTA
        elif current_coord.longitude < checkpoints[current_checkpoint].longitude:
            current_coord.latitude += DELTA
        if current_coord.longitude > checkpoints[current_checkpoint].longitude:
            current_coord.longitude -= DELTA
        elif current_coord.longitude < checkpoints[current_checkpoint].longitude:
            current_coord.longitude += DELTA
        if abs(current_coord.latitude-checkpoints[current_checkpoint].latitude) < DELTA \
                and abs(current_coord.longitude-checkpoints[current_checkpoint].longitude) < DELTA:
            current_checkpoint += 1
        if current_checkpoint == len(checkpoints):
            current_checkpoint = 0
            current_coord = start_tuple
        payload = {
            "raceId": RACE_ID_TO_JOIN,
            "latitude": current_coord.latitude,
            "longitude": current_coord.longitude
        }
        res = req.get(BASE_URL+"UpdatePosition", params=payload, auth=user_tuple)
        print res.text
        time.sleep(3)

if __name__ == '__main__':
    start_coords = []
    auths = []
    checks = []
    threads = []
    res = req.get(BASE_URL+"GetRaceInfo", params={"raceId": RACE_ID_TO_JOIN}, auth=('milesh', 'nope123'))
    checkpoints = res.json()["checkPoints"]
    for checkpoint in checkpoints:
        checks.append(CheckPoint(checkpoint['latitude'], checkpoint['longitude']))
    for i in range(USER_NUM):
        start_coords.append(CheckPoint(checkpoints[0]['latitude']+random.uniform(-.01, .01),
                                       checkpoints[0]['longitude']+random.uniform(-.01, .01)))
        auths.append((USER_STRING+str(i), USER_PASS))
    for i in range(USER_NUM):
        threads.append(threading.Thread(target=race_thread, args=(auths[i], start_coords[i], checks)))
        threads[i].start()