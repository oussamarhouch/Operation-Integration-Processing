import {
  Box,
  Icon,
  SimpleGrid,
  useColorModeValue,
} from "@chakra-ui/react";

import {RiFileTransferFill} from "react-icons/ri";
import {BsFillFileMinusFill} from "react-icons/bs"

import MiniStatistics from "components/card/MiniStatistics";
import IconBox from "components/icons/IconBox";
import React from "react";
import {
  MdFileCopy,
} from "react-icons/md";
import DailyTraffic from "views/admin/rej120/components/DailyTraffic";
import PieCard from "views/admin/rej120/components/PieCard";
import TotalSpent from "views/admin/rej120/components/TotalSpent";

import {nombreLot, nombreRej } from "services/LOT/rej120";
import {rejetRefuse} from "services/CRL/rej120";

export default function UserReports() {

  const brandColor = useColorModeValue("brand.500", "white");
  const boxBg = useColorModeValue("secondaryGray.300", "whiteAlpha.100");
  const [nombredeLot, setNombredeLot] = React.useState(null);
  const [totalRejetRefuse, setTotalRejetRefuse] = React.useState(null);
  const [nombreRejet, setNombreRejet] = React.useState(null);

  React.useEffect(() => {
    fetchNombreLot();
    fetchRejetRefuse();
    fetchNombreRejet();
  }, []);

  const fetchNombreLot = async () => {
    try {
      const nombre = await nombreLot();
      setNombredeLot(nombre);
    } catch (error) {
      console.error("Error fetching nombreLot:", error);
    }
  };

  
  const fetchRejetRefuse = async () => {
    try {
      const rejet = await rejetRefuse();
      setTotalRejetRefuse(rejet);
    } catch (error) {
      console.error("Error fetching rejetRefuse:", error);
    }
  };

  const fetchNombreRejet = async () => {
    try {
      const nombreRejet = await nombreRej();
      setNombreRejet(nombreRejet);
    } catch (error) {
      console.error("Error fetching nombreRejet:", error);
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
          name="Nombre de rejets initiales"
          value={nombreRejet}
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
          name='Nombre de rejets refusés'
          value={totalRejetRefuse}
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
