import axios from "axios";

const API_URL = 'http://localhost:8080/api/auth';

const login = async (username, password) => {
    try {
        const response = await axios.post(`${API_URL}/signin`, { username, password });
        const token = response.data.token;
        localStorage.setItem("token", token);
        return token;
    } catch (error) {
        throw new Error('Login failed');
    }
};

export default login;
