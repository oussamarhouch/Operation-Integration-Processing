import axios from "axios";

const API_URL = 'http://localhost:8080/api/lot';

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
    const response = await axios.get(`${API_URL}/013/${formattedDate}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error.message);
    throw error;
  }
};

const nombreLot = async () => {
  const lot = await fetchDataFromApi();
  return lot.length;
};

const montantTotal = async () => {
  const lot = await fetchDataFromApi();
  const totalMontant = lot.reduce((total, item) => total + item.montant, 0);
  return totalMontant;
};

const nombreOp = async () => {
  const lot = await fetchDataFromApi();
  const nombreOperation = lot.reduce((total, item) => total + item.nombreOperation, 0);
  return nombreOperation;
};

const nombreOperationListLastSevenDays = async () => {
  try {
    const opList = [];

    for (let i = 6; i >= 0; i--) {
      const date = new Date();
      date.setDate(date.getDate() - i);
      const formattedDate = formatDate(date);
      
      try {
        const response = await axios.get(`${API_URL}/013/${formattedDate}`);
        const lot = response.data;
        
        const opSum = lot.reduce((total, item) => total + item.nombreOperation, 0);
        opList.push(opSum);
      } catch (error) {
        if ((error.response && error.response.status === 500) || (error.response && error.response.status === 404)) {
          opList.push(0);
        } else {
          console.error("Error fetching data:", error.message);
          throw error;
        }
      }
    }
    
    return opList;
  } catch (error) {
    console.error("Error:", error.message);
    throw error;
  }
};
const nombreLot013 = async() => {
  const lot = await fetchDataFromApi();
  return lot.length;
}

const nombreOp013 = async () => {
  const lot = await fetchDataFromApi();
  const nombreOperation = lot.reduce((total, item) => total + item.nombreOperation, 0);
  return nombreOperation;
}

const montantTotal013 = async () => {
  const lot = await fetchDataFromApi();
  const totalMontant = lot.reduce((total, item) => total + item.montant, 0);
  return totalMontant;
}

const opLastSixDays013 = async() => {
  return await nombreOperationListLastSevenDays();
}

export {opLastSixDays013, montantTotal013, nombreOp013, nombreLot, nombreLot013, montantTotal, nombreOp, nombreOperationListLastSevenDays};
