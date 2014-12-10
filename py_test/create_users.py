__author__ = 'Miles'

import requests as req

USER_STRING = "user"
USER_PASS = "shitty_password"
BASE_URL = "http://racegofer.com/api/"
USER_NUM = 100


def create_users(num_users):
    for i in range(20, num_users):
        username = USER_STRING + str(i)
        print "Creating user "+username
        payload = {
            "userName": username,
            "password": USER_PASS,
            "firstName": "user",
            "lastName": str(i),
            "phoneNumber": "4206666969",
            "email": "root@gmail.com",
        }
        res = req.get(BASE_URL+"RegisterUser", params=payload)
        if res.status_code != 200:
            raise Exception(username, res.status_code)


if __name__ == '__main__':
    create_users(USER_NUM)