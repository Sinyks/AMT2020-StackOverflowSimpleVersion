# ---------------------------------------------------------------------------- #
#                  Python automation script for stting up API                  #
# ---------------------------------------------------------------------------- #

import api_config as config
import requests as req
from dotenv import load_dotenv
from pathlib import Path
import os
import json
import time

if __name__ == "__main__":

    # ------------------------- import environement value ------------------------ #

    env_path = Path('..') / '.env'
    load_dotenv(dotenv_path=env_path)

    # ------------------------------ Prepare payload ----------------------------- #

    api_url = "http://{}:{}".format(os.getenv('API_HOST'),os.getenv('API_PORT'))

    headers = {
        'accept': 'application/json',
        'Content-Type': 'application/json',
    }

    print(api_url + "/applications")

    data = {"name": config.APPLICATION_NAME}
    
    try:

        # -------------------------------- Get and set API-KEY ------------------------ #

        resp = req.post(url=api_url + "/applications",data=json.dumps(data),headers=headers).json()
        headers["X-API-KEY"] = resp['key']
        print("Your api key is {}".format(headers["X-API-KEY"]))

        # -------------------------------- Set badges -------------------------------- #

        print("create badges")

        for badge in config.BADGES:
            req.post(api_url+"/badges",data=json.dumps(badge),headers=headers)
        
        # ------------------------------ Set pointScale ------------------------------ #

        print("create pointscales")
        
        for pointScale in config.POINTSCALES:
            req.post(api_url+"/pointscale",data=json.dumps(pointScale),headers=headers)
        
        # --------------------------------- Set Rules -------------------------------- #

        print("create rules")

        for rule in config.RULES:
            req.post(api_url+"/rules",data=json.dumps(rule),headers=headers)

    except json.JSONDecodeError:
        print("error while decoding the response of {}, this is not JSON format".format(api_url))
    except req.HTTPError as e:
        print("There was an invalid HTTP response code ", e)
