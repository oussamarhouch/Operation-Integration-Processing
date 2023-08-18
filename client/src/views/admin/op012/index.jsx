import {
  Box,
  Icon,
  SimpleGrid,
  useColorModeValue,
} from "@chakra-ui/react";

import {RiFileTransferFill} from "react-icons/ri";
import {BsFillFileMinusFill} from "react-icons/bs";

import MiniStatistics from "components/card/MiniStatistics";
import IconBox from "components/icons/IconBox";
import React from "react";
import {
  MdAttachMoney,
  MdFileCopy,
} from "react-icons/md";
import DailyTraffic from "views/admin/op012/components/DailyTraffic";
import PieCard from "views/admin/op012/components/PieCard";
import TotalSpent from "views/admin/op012/components/TotalSpent";

import {nombreLot, montantTotal, nombreOp} from "services/LOT/op012";
import {nombreOpCRL} from "services/CRL/op012";
import {montantCRA} from "services/CRA/op012";
import {montantCRO} from "services/CRO/op012";

export default function UserReports() {

  const brandColor = useColorModeValue("brand.500", "white");
  const boxBg = useColorModeValue("secondaryGray.300", "whiteAlpha.100");
  const [nombredeLot, setNombredeLot] = React.useState(null);
  const [montantInitial, setMontantInitial] = React.useState(null);
  const [nombreOperation, setNombreOperation] = React.useState(null);
  const [operationRejete, setOperationRejete] = React.useState(null);
  const [montantAccepte, setMontantAccepte] = React.useState(null);
  const [montantdeCRO, setMontantdeCRO] = React.useState(null);

  React.useEffect(() => {
    fetchNombreLot();
    fetchMontantInitial();
    fetchNombreOperation();
    fetchOperationRejete();
    fetchMontantAccepte();
    fetchMontantdeCRO();
  }, []);

  const fetchNombreLot = async () => {
    try {
      const nombre = await nombreLot();
      setNombredeLot(nombre);
    } catch (error) {
      console.error("Error fetching nombreLot:", error);
    }
  };

  const formatMontantWithDots = (montant) => {
    const formattedMontant = montant.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    return formattedMontant;
  };
  
  const fetchMontantInitial = async () => {
    try {
      const montant = await montantTotal();
      const formattedMontant = formatMontantWithDots(montant);
      setMontantInitial(formattedMontant);
    } catch (error) {
      console.error("Error fetching montantTotal:", error);
    }
  };

  const fetchNombreOperation = async () => {
    try {
      const nombreOperation = await nombreOp();
      setNombreOperation(nombreOperation);
    } catch (error) {
      console.error("Error fetching nombreOperation:", error);
    }
  };

  const fetchOperationRejete = async () => {
    try {
      const nombreOperation = await nombreOpCRL();
      setOperationRejete(nombreOperation);
    } catch (error) {
      console.error("Error fetching nombreOperation:", error);
    }
  };

   const fetchMontantAccepte = async () => {
    try {
      const montant = await montantCRA();
      const montantformatted = formatMontantWithDots(montant);
      setMontantAccepte(montantformatted);
    } catch (error) {
      console.error("Error fetching montantAccepte:", error);
    }
  };

  const fetchMontantdeCRO = async () => {
    try {
      const montant = await montantCRO();
      const montantformatted = formatMontantWithDots(montant);
      setMontantdeCRO(montantformatted);
    } catch (error) {
      console.error("Error fetching montantCRO:", error);
    }
  };
  
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
          name='Nombre de LOT'
          value={nombredeLot}
        />
        <MiniStatistics
          startContent={
            <IconBox
              w='56px'
              h='56px'
              bg={boxBg}
              icon={
                <Icon w='32px' h='32px' as={RiFileTransferFill} color={brandColor} />
              }
            />
          }
          name="Nombre d'opérations initiales"
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
          name='Montant Initial'
          value={montantInitial}
        />
        <MiniStatistics
          startContent={
            <IconBox
              w='56px'
              h='56px'
              bg={boxBg}
              icon={
                <Icon w='32px' h='32px' as={BsFillFileMinusFill} color={brandColor} />
              }
            />
          }
          name='Opérations Rejetés'
          value={operationRejete}
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
          name='Montant du CRO'
          value={montantdeCRO}
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
          name='Montant Accepté'
          value={montantAccepte}
        />


        
      </SimpleGrid>

      <SimpleGrid columns={{ base: 1, md: 2, xl: 2 }} gap='20px' mb='20px'>
        <TotalSpent />
        <SimpleGrid columns={{ base: 1, md: 2, xl: 2 }} gap='20px'>
          <DailyTraffic />
          <PieCard />
        </SimpleGrid>
      </SimpleGrid>
    </Box>
  );
}
