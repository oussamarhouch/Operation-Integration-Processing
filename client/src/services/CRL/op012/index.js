import axios from "axios";

const API_URL = 'http://localhost:8080/api/crl';

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
    const response = await axios.get(`${API_URL}/operation/012/${formattedDate}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error.message);
    throw error;
  }
};


const montantCRL = async () => {
  const crl = await fetchDataFromApi();
  const totalMontant = crl.reduce((total, item) => total + item.montant, 0);
  return totalMontant;
};

const nombreOpCRL = async () => {
  const crl = await fetchDataFromApi();
  const nombreOperation = crl.reduce((total, item) => total + 1, 0);
  return nombreOperation;
};

const montantRejeteListLastSixDays = async () => {
  try {
    const montantList = [];

    for (let i = 5; i >= 0; i--) {
      const date = new Date();
      date.setDate(date.getDate() - i);
      const formattedDate = formatDate(date);
      
      try {
        const response = await axios.get(`${API_URL}/operation/012/${formattedDate}`);
        const crl = response.data;
        
        const montantSum = crl.reduce((total, item) => total + item.montant, 0);
        montantList.push(montantSum);
      } catch (error) {
        if ((error.response && error.response.status === 500) || (error.response && error.response.status === 404)) {
          montantList.push(0);
        } else {
          console.error("Error fetching data:", error.message);
          throw error;
        }
      }
    }
    
    return montantList;
  } catch (error) {
    console.error("Error:", error.message);
    throw error;
  }
};

const nombreOpCRL012 = async() => {
  const crl = await fetchDataFromApi();
  const nombreOperation = crl.reduce((total, item) => total + 1, 0);
  return nombreOperation;
}

export {nombreOpCRL012, montantCRL, nombreOpCRL, montantRejeteListLastSixDays };
