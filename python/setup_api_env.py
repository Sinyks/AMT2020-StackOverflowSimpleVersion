import user_env as env
import api_config as config
import requests as req
import json
import time

if __name__ == "__main__":
    # create the host api url
    api_url = "http://{}:{}".format(env.HOST,env.PORT)

    headers = {
        'accept': 'application/json',
        'Content-Type': 'application/json',
    }

    # get the application key
    print(api_url + "/applications")

    data = {"name": env.APP_NAME}
    
    try:
        resp = req.post(url=api_url + "/applications",data=json.dumps(data),headers=headers).json()
        headers["X-API-KEY"] = resp['key']
        print("My api key is {}".format(headers["X-API-KEY"]))

        print("create badges")
        time.sleep(2)

        for badge in config.BADGES:
            req.post(api_url+"/badges",data=json.dumps(badge),headers=headers)
        
        print("create pointscales")
        time.sleep(2)
        
        for pointScale in config.POINTSCALES:
            req.post(api_url+"/pointscale",data=json.dumps(pointScale),headers=headers)
        
        print("create rules")
        time.sleep(2)

        for rule in config.RULES:
            req.post(api_url+"/rules",data=json.dumps(rule),headers=headers)

    except json.JSONDecodeError:
        print("error while decoding the response of {}, this is not JSON format".format(api_url))
    except req.HTTPError as e:
        print("There was an invalid HTTP response code ", e)
