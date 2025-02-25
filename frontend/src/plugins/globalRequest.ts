import {extend} from 'umi-request';
import {message} from "antd";
import {stringify} from "querystring";

const request = extend({
  credentials: 'include',
  prefix: process.env.NODE_ENV === 'development' ? undefined : 'https://user-backend.code-nav.cn',
});


request.interceptors.request.use((url, options): any => {
  console.log(`do request url = ${url}`);

  return {
    url,
    options: { ...options, headers:{}},
  };
});


request.interceptors.response.use(async (response, options): Promise<any> => {
  const res = await response.clone().json();
  if (res.code === 0) {
    return res.data;
  }
  if (res.code === 40100) {
    message.error("Please Login first");
    history.replace({
      pathname: '/user/login',
      search: stringify({
        redirect: location.pathname,
      }),
    })
  } else {
    message.error(res.description);
  }
  return res.data;
});


export default request;
