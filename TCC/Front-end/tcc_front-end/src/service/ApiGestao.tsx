import axios from "axios";

export const ApiGestao = axios.create({
  baseURL: "http://localhost:8080"
});

export const setAuthToken = (token: string) => {
  ApiGestao.defaults.headers.common["Authorization"] = `${token}`;
};

ApiGestao.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error?.response?.status === 401 && error.response.config.url !== '/login') {
      window.location.href = '/login';
    }
  }
);