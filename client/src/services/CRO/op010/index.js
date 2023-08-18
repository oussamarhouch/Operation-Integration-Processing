import axios from "axios";

const API_URL = 'http://localhost:8080/api/cro';

const formatDate = (date) => {
  const day = String(date.getDate()).padStart(2, "0");
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const year = date.getFullYear();
  return `${day}${month}${year}`;
};
const today = new Date();
const formattedDate = formatDate(today);

const fetchDataFromApi = async () => {
  try {
    const response = await axios.get(`${API_URL}/operation/010/${formattedDate}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error.message);
    throw error;
  }
};


const montantCRO = async () => {
  const cro = await fetchDataFromApi();
  const totalMontant = cro.reduce((total, item) => total + item.montant, 0);
  return totalMontant;
};


export { montantCRO};
