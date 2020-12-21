import user_env as env
import requests as req
import json

if __name__ == "__main__":
    # create the host api url

    api_url = "http://{}:{}".format(env.HOST,env.PORT)

    headers = {
    'accept': 'application/json',
    'Content-Type': 'application/json',
    }

    print(api_url)

    # get the application key
    api_url += "/applications"
    print(api_url)

    data = {"name": env.APP_NAME}
    
    try:
        resp = req.post(url=api_url,data=json.dumps(data),headers=headers).json()
        key = resp['key']
    except json.JSONDecodeError:
        print("error while decoding the response of {}, this is not JSON format".format(api_url))
    except req.HTTPError as e:
        print("There was an invalid HTTP response code ", e)
    
    print("My api key is {}".format(key))
    headers["X-API-KEY"] = key
