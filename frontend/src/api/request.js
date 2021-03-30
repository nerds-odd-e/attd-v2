import axios from 'axios'

// create an axios instance
const service = axios.create({
  baseURL: 'http://localhost:10080', // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    return config
  },
  error => {
    // eslint-disable-next-line no-console
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  response => {
    return response
  },
  error => {
    // eslint-disable-next-line no-console
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

export default service
