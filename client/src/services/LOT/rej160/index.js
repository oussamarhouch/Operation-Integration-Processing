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
    const response = await axios.get(`${API_URL}/160/${formattedDate}`);
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



const nombreRej = async () => {
  const lot = await fetchDataFromApi();
  const nombreRejet = lot.reduce((total, item) => total + item.nombreRejet, 0);
  return nombreRejet;
};

const nombreRejetListLastSevenDays = async () => {
  try {
    const opList = [];

    for (let i = 6; i >= 0; i--) {
      const date = new Date();
      date.setDate(date.getDate() - i);
      const formattedDate = formatDate(date);
      
      try {
        const response = await axios.get(`${API_URL}/160/${formattedDate}`);
        const lot = response.data;
        
        const opSum = lot.reduce((total, item) => total + item.nombreRejet, 0);
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

const nombreLot160 = async() => {
  const lot = await fetchDataFromApi();
  return lot.length;
}

const nombreRej160 = async () => {
  const lot = await fetchDataFromApi();
  const nombreRejet = lot.reduce((total, item) => total + item.nombreRejet, 0);
  return nombreRejet;
}

const opLastSixDays160 = async() => {
  return await nombreRejetListLastSevenDays();
}

export {opLastSixDays160, nombreRej160, nombreLot, nombreLot160, nombreRej, nombreRejetListLastSevenDays};
