import { Login } from "../../models/login/index";
import { ApiGestao } from "../ApiGestao";

//Post
export const postLogin = async (data: Login) => {
    try {
      const response = await ApiGestao.post("/login", data);
      const token = response?.headers['authorization'];
      return { response, token };
    } catch (error) {
      console.error("Erro ao fazer login ", error);
      throw error;
    }
  };