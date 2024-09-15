import { postLogin } from "../../service/ApiGestaoLogin";
import { jwtDecode } from "jwt-decode";

interface JwtPayload {
  sub: string;
  exp: string;
  authorities: string;
}

const validateUser = async (
  username: string,
  password: string,
  errorCallback: () => void,
  successCallback: (authorities: string[], token: string, tokenExp: string) => void
) => {
  if (username && password) {
    const data = {
      username,
      password,
    };
    
    try {
      const { response, token } = await postLogin(data)
      console.log(response.status);
      if (response.status === 200) {
        const decodedToken = jwtDecode<JwtPayload>(token);

        // console.log("Token: ", decodedToken);
        
        const authorities = decodedToken.authorities.split(',');

        const tokenExp = decodedToken.exp;

        successCallback(authorities, token, tokenExp);
      } else {
        console.log(
          "Login não realizado!",
          response.data.error || "Aconteceu algum erro..."
        );
        errorCallback();
      }
    } catch (error: any) {
      if (error.response && error.response.status === 401) {
        console.log("Erro ao realizar login: ", error.response.data);
        errorCallback();
      } else {
        console.log("Erro na requisição!", error);
        errorCallback();
      }
    }
  }
};

export { validateUser };