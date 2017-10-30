private string WEB_HOOK = "https://oapi.dingtalk.com/robot/send?access_token=d2d22ad140485e7049498d6062ff9d807b0cfe31233d5a74994e0fae7e64dcf7";  
  
       private void buttonTest_Click(object sender, EventArgs e)  
       {  
           try  
           {  
               string msg = textBox1.Text;  
               String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"" + msg + "\"}}";  
               string s = Post(WEB_HOOK, textMsg, null);  
               MessageBox.Show(s);  
           }  
           catch (Exception ex)  
           {  
               MessageBox.Show(ex.Message);  
           }  
       }  
 
       #region Post  
       /// <summary>  
       /// 以Post方式提交命令  
       /// </summary>  
       /// <param name="apiurl">请求的URL</param>  
       /// <param name="jsonString">请求的json参数</param>  
       /// <param name="headers">请求头的key-value字典</param>  
       public static String Post(string apiurl, string jsonString, Dictionary<String, String> headers = null)  
       {  
           WebRequest request = WebRequest.Create(@apiurl);  
           request.Method = "POST";  
           request.ContentType = "application/json";  
           if (headers != null)  
           {  
               foreach (var keyValue in headers)  
               {  
                   if (keyValue.Key == "Content-Type")  
                   {  
                       request.ContentType = keyValue.Value;  
                       continue;  
                   }  
                   request.Headers.Add(keyValue.Key, keyValue.Value);  
               }  
           }  
  
           if (String.IsNullOrEmpty(jsonString))  
           {  
               request.ContentLength = 0;  
           }  
           else  
           {  
               byte[] bs = Encoding.UTF8.GetBytes(jsonString);  
               request.ContentLength = bs.Length;  
               Stream newStream = request.GetRequestStream();  
               newStream.Write(bs, 0, bs.Length);  
               newStream.Close();  
           }  
  
  
           WebResponse response = request.GetResponse();  
           Stream stream = response.GetResponseStream();  
           Encoding encode = Encoding.UTF8;  
           StreamReader reader = new StreamReader(stream, encode);  
           string resultJson = reader.ReadToEnd();  
           return resultJson;  
       }  
