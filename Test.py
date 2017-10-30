import json,urllib2,time
def dd():
    url='https://oapi.dingtalk.com/robot/send?access_token=ed071555913073b6dc679c19371df962e2493e2e4c3516b229a92533ea624d6f'
    con={"msgtype":"text","text":{"content":"Hello from PYTHON !!!"}}
    jd=json.dumps(con)
    req=urllib2.Request(url,jd)
    req.add_header('Content-Type', 'application/json')
    response=urllib2.urlopen(req)
    print response,time.ctime() 
if __name__ == '__main__':
    dd()

