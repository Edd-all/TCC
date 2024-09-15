import { Usuario } from "../../models/usuario/index";
import { ApiGestao } from "../ApiGestao";

//Get
export const getUsuarios = async () => {
  try {
    const response = await ApiGestao.get("/usuario/listar");
    return response.data;
  } catch (error) {
    console.error("Erro ao buscar usuários", error);
    throw error;
  }
};

//GetId
export const getUsuarioPorID = async (id: number) => {
  try {
    const response = await ApiGestao.get(`/usuario/listarPorId/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Erro ao buscar o usuário com ID ${id}`, error);
    throw error;
  }
};

//Post
export const postUsuario = async (data: Omit<Usuario, "id">) => {
  try {
    const response = await ApiGestao.post("/usuario/cadastrar", data);
    return response.data;
  } catch (error) {
    console.error("Erro ao cadastrar usuário", error);
    throw error;
  }
};

//Put
export const putUsuario = async (id: number, data: Omit<Usuario, "id">) => {
  try {
    const response = await ApiGestao.put(`/usuario/atualizar/${id}`, data);
    return response.data;
  } catch (error) {
    console.error(`Erro ao atualizar o usuário com ID ${id}`, error);
    throw error;
  }
};

//Delete
export const deleteUsuario = async (id: number) => {
  try {
    const response = await ApiGestao.delete(`/usuario/deletar/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Erro ao deletar o usuário com ID ${id}`, error);
    throw error;
  }
};