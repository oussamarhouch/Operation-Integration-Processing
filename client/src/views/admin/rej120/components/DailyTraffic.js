import React from "react";

import { Box, Flex, Text, useColorModeValue } from "@chakra-ui/react";
import BarChart from "components/charts/BarChart";

import Card from "components/card/Card.js";
import {
  barChartDataDailyTraffic,
  barChartOptionsDailyTraffic,
} from "views/admin/rej120/variables/charts";
import {nombreRejetListLastSevenDays} from "services/LOT/rej120";

export default function DailyTraffic(props) {
  const { ...rest } = props;

  const textColor = useColorModeValue("secondaryGray.900", "white");
  const [nombreRejet, setNombreRejet] = React.useState(null);
  React.useEffect(() => {
    fetchNombreRejet();
  }, []);
  const fetchNombreRejet = async () => {
    try {
      const nombreRejetList = await nombreRejetListLastSevenDays();
      var nombreRejet = 0;
      for (let i = 0; i < nombreRejetList.length; i++) {
        nombreRejet += nombreRejetList[i];
      }
      setNombreRejet(nombreRejet);
    } catch (error) {
      console.error("Error fetching nombreRejet:", error);
    }
  };
  return (
    <Card align='center' direction='column' w='100%' {...rest}>
      <Flex justify='space-between' align='start' px='10px' pt='5px'>
        <Flex flexDirection='column' align='start' me='20px'>
          <Flex w='100%'>
            <Text
              me='auto'
              color='secondaryGray.600'
              fontSize='sm'
              fontWeight='500'>
              total derniers 7 jours:
            </Text>
          </Flex>
          <Flex align='end'>
            <Text
              color={textColor}
              fontSize='34px'
              fontWeight='700'
              lineHeight='100%'>
              {nombreRejet}
            </Text>
            <Text
              ms='6px'
              color='secondaryGray.600'
              fontSize='sm'
              fontWeight='500'>
              Rejets
            </Text>
          </Flex>
        </Flex>
      </Flex>
      <Box h='240px' mt='auto'>
        <BarChart
          chartData={barChartDataDailyTraffic}
          chartOptions={barChartOptionsDailyTraffic}
        />
      </Box>
    </Card>
  );
}
