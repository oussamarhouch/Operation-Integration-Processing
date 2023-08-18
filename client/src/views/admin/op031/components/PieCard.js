import { Box, Flex, Text, useColorModeValue } from "@chakra-ui/react";

import Card from "components/card/Card.js";
import PieChart from "components/charts/PieChart";
import { pieChartData, pieChartOptions } from "views/admin/op031/variables/charts";
import { VSeparator } from "components/separator/Separator";
import React from "react";
import { nombreOpCRA } from "services/CRA/op031";
import { nombreOpCRL } from "services/CRL/op031";

export default function Conversion(props) {
  const { ...rest } = props;

  const textColor = useColorModeValue("secondaryGray.900", "white");
  const cardColor = useColorModeValue("white", "navy.700");
  const cardShadow = useColorModeValue(
    "0px 18px 40px rgba(112, 144, 176, 0.12)",
    "unset"
  );
  const [nombreOperationCRL, setNombreOperationCRL] = React.useState(null);
  const [nombreOperationCRA, setNombreOperationCRA] = React.useState(null);
    console.log(nombreOperationCRL)
  React.useEffect(() => {
    fetchNombreOpCRL();
    fetchNombreOpCRA();
  }, []);

  const fetchNombreOpCRL = async () => {
    try {
      const nombre = await nombreOpCRL();
      setNombreOperationCRL(nombre);
    } catch (error) {
      console.error("Error fetching nombreLot:", error);
    }
  };

  const fetchNombreOpCRA = async () => {
    try {
      const nombre = await nombreOpCRA();
      setNombreOperationCRA(nombre);
    } catch (error) {
      console.error("Error fetching nombreLot:", error);
    }
  };

  return (
    <Card p='20px' align='center' direction='column' w='100%' {...rest}>
      <Flex
        px={{ base: "0px", "2xl": "10px" }}
        justifyContent='space-between'
        alignItems='center'
        w='100%'
        mb='8px'>
        <Text color={textColor} fontSize='md' fontWeight='600' mt='4px'>
           
        </Text>
        <Text
          fontSize='sm'
          variant='subtle'
          defaultValue='monthly'
          width='unset'
          fontWeight='700'>Aujourd'hui
        </Text>
      </Flex>

      <PieChart
        h='100%'
        w='100%'
        chartData={pieChartData}
        chartOptions={pieChartOptions}
      />
      <Card
        bg={cardColor}
        flexDirection='row'
        boxShadow={cardShadow}
        w='100%'
        p='15px'
        px='20px'
        mt='15px'
        mx='auto'>
        <Flex direction='column' py='5px'>
          <Flex align='center'>
            <Box h='8px' w='8px' bg='brand.500' borderRadius='50%' me='4px' />
            <Text
              fontSize='xs'
              color='secondaryGray.600'
              fontWeight='700'
              mb='5px'>
              Acceptation
            </Text>
          </Flex>
          <Text fontSize='lg' color={textColor} fontWeight='700'>
          {(100 * nombreOperationCRA/(nombreOperationCRA + nombreOperationCRL)).toFixed(2)} %
          </Text>
        </Flex>
        <VSeparator mx={{ base: "10px", xl: "25px", "2xl": "30px" }} />
        <Flex direction='column' py='5px' me='10px'>
          <Flex align='center'>
            <Box h='8px' w='8px' bg='#6AD2FF' borderRadius='50%' me='4px' />
            <Text
              fontSize='xs'
              color='secondaryGray.600'
              fontWeight='700'
              mb='5px'>
              Rejet
            </Text>
          </Flex>
          <Text fontSize='lg' color={textColor} fontWeight='700'>
          {(100 * nombreOperationCRL/(nombreOperationCRA + nombreOperationCRL)).toFixed(2)} %
          </Text>
        </Flex>
      </Card>
    </Card>
  );
}
