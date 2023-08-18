import {
  Box,
  Icon,
  SimpleGrid,
  useColorModeValue,
} from "@chakra-ui/react";

import MiniStatistics from "components/card/MiniStatistics";
import IconBox from "components/icons/IconBox";
import React from "react";
import {
  MdAttachMoney,
  MdFileCopy,
} from "react-icons/md";
import DailyTraffic from "views/admin/default/components/DailyTraffic";
import PieCard from "views/admin/default/components/PieCard";
import WeeklyRevenue from "views/admin/default/components/WeeklyRevenue";
import { nombreLot010 } from "services/LOT/op010";
import { nombreLot012 } from "services/LOT/op012";
import { nombreLot013 } from "services/LOT/op013";
import { nombreLot020 } from "services/LOT/op020";
import { nombreLot031 } from "services/LOT/op031";
import { nombreLot060 } from "services/LOT/op060";
import { nombreLot120 } from "services/LOT/rej120";
import { nombreLot131 } from "services/LOT/rej131";
import { nombreLot160 } from "services/LOT/rej160";
import { nombreOp010 } from "services/LOT/op010";
import { nombreOp012 } from "services/LOT/op012";
import { nombreOp013 } from "services/LOT/op013";
import { nombreOp020 } from "services/LOT/op020";
import { nombreOp060 } from "services/LOT/op060";
import { nombreOp031 } from "services/LOT/op031";
import { montantTotal010 } from "services/LOT/op010";
import { montantTotal012 } from "services/LOT/op012";
import { montantTotal013 } from "services/LOT/op013";
import { montantTotal020 } from "services/LOT/op020";
import { montantTotal060 } from "services/LOT/op060";
import { montantTotal031 } from "services/LOT/op031";
import { nombreRej120 } from "services/LOT/rej120";
import { nombreRej131 } from "services/LOT/rej131";
import { nombreRej160 } from "services/LOT/rej160";
import { nombreRejCRA120 } from "services/CRA/rej120";
import { nombreRejCRA131 } from "services/CRA/rej131";
import { nombreRejCRA160 } from "services/CRA/rej160";
import { montantTotalCRA010} from "services/CRA/op010";
import { montantTotalCRA012} from "services/CRA/op012";
import { montantTotalCRA013} from "services/CRA/op013";
import { montantTotalCRA020} from "services/CRA/op020";
import { montantTotalCRA031} from "services/CRA/op031";
import { montantTotalCRA060} from "services/CRA/op060";

export default function UserReports() {

  const brandColor = useColorModeValue("brand.500", "white");
  const boxBg = useColorModeValue("secondaryGray.300", "whiteAlpha.100");

  const formatMontantWithDots = (montant) => {
    const formattedMontant = montant.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    return formattedMontant;
  };

  const fetchNombreLot = async () => {
    try {
      let nombre = 0;
      nombre += await nombreLot010() + await nombreLot012() + await nombreLot013() + await nombreLot020() + await nombreLot031() + await nombreLot060() + await nombreLot120() + await nombreLot131() + await nombreLot160();
      setNombredeLot(nombre);
    } catch (error) {
      console.error("Error fetching nombreLot:", error);
    }
  };

  const fetchNombreOperation = async () => {
    try{
      let nombre = 0;
    nombre += await nombreOp010() + await nombreOp012() + await nombreOp013() + await nombreOp020() + await nombreOp031() + await nombreOp060();
    setNombreOperation(nombre);
    } catch(error){
      console.error("error fetching nombreOperation: ", error);
    }
  }

  const fetchNombreRejet = async () => {
    try{
      let nombre = 0;
    nombre += await nombreRej120() + await nombreRej131() + await nombreRej160();
    setNombreRejet(nombre);
    } catch(error){
      console.error("error fetching nombreRejet: ", error);
    }
  }

  const fetchRejetAccepte = async () => {
    try{
      let nombre = 0;
    nombre += await nombreRejCRA120() + await nombreRejCRA131() + await nombreRejCRA160();
    setRejetAccepte(nombre);
    } catch(error){
      console.error("error fetching rejetAccepte: ", error);
    }
  }

  const fetchMontantTotal = async () => {
    try{
      let montant = 0;
    montant += await montantTotal010() + await montantTotal012() + await montantTotal013() + await montantTotal020() + await montantTotal031() + await montantTotal060();
    setMontantTotal(formatMontantWithDots(montant));
    } catch(error){
      console.error("error fetching montantTotal: ", error);
    }
  }

  const fetchMontantAccepte = async () => {
    try{
      let montant = 0;
    montant += await montantTotalCRA010() + await montantTotalCRA012() + await montantTotalCRA013() + await montantTotalCRA020() + await montantTotalCRA031() + await montantTotalCRA060();
    setMontantAccepte(formatMontantWithDots(montant));
    } catch(error){
      console.error("error fetching montantTotal: ", error);
    }
  }
  
  const [nombredeLot, setNombredeLot] = React.useState(null);
  const [nombreOperation, setNombreOperation] = React.useState(null);
  const [nombreRejet, setNombreRejet] = React.useState(null);
  const [montantTotal, setMontantTotal] = React.useState(null);
  const [rejetAccepte, setRejetAccepte] = React.useState(null);
  const [montantAccepte, setMontantAccepte] = React.useState(null);
  React.useEffect(() => {
    fetchNombreLot();
    fetchNombreOperation();
    fetchMontantTotal();
    fetchNombreRejet();
    fetchRejetAccepte();
    fetchMontantAccepte();
  }, []);

  

  return (
    <Box pt={{ base: "130px", md: "80px", xl: "80px" }}>
      <SimpleGrid
        columns={{ base: 1, md: 2, lg: 3, "2xl": 6 }}
        gap='20px'
        mb='20px'>
        <MiniStatistics
          startContent={
            <IconBox
              w='56px'
              h='56px'
              bg={boxBg}
              icon={
                <Icon w='32px' h='32px' as={MdFileCopy} color={brandColor} />
              }
            />
          }
          name='Nombre Total des LOTs'
          value={nombredeLot}
        />
        <MiniStatistics
          startContent={
            <IconBox
              w='56px'
              h='56px'
              bg={boxBg}
              icon={
                <Icon w='32px' h='32px' as={MdAttachMoney} color={brandColor} />
              }
            />
          }
          name='Nombre des Opérations initiales'
          value={nombreOperation}
        />
        <MiniStatistics
          startContent={
            <IconBox
              w='56px'
              h='56px'
              bg={boxBg}
              icon={
                <Icon w='32px' h='32px' as={MdAttachMoney} color={brandColor} />
              }
            />
          }
          name='Montant initial total'
          value={montantTotal}
        />
        <MiniStatistics
          startContent={
            <IconBox
              w='56px'
              h='56px'
              bg={boxBg}
              icon={
                <Icon w='32px' h='32px' as={MdAttachMoney} color={brandColor} />
              }
            />
          }
          name='Nombre des Rejets initiales'
          value={nombreRejet}
        />
        
        <MiniStatistics
          startContent={
            <IconBox
              w='56px'
              h='56px'
              bg={boxBg}
              icon={
                <Icon w='32px' h='32px' as={MdAttachMoney} color={brandColor} />
              }
            />
          }
          name='Nombre des Rejets Acceptés'
          value={rejetAccepte}
        />
        <MiniStatistics
          startContent={
            <IconBox
              w='56px'
              h='56px'
              bg={boxBg}
              icon={
                <Icon w='32px' h='32px' as={MdAttachMoney} color={brandColor} />
              }
            />
          }
          name='Montant Total Accepté'
          value={montantAccepte}
        />
      </SimpleGrid>

      <SimpleGrid columns={{ base: 1, md: 2, xl: 2 }} gap='20px' mb='20px'>
      <SimpleGrid columns={{ base: 1, md: 2, xl: 2 }} gap='20px'>
          <DailyTraffic />
          <PieCard />
        </SimpleGrid>
        <WeeklyRevenue />
      </SimpleGrid>
    </Box>
  );
}
