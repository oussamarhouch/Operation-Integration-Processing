import React from "react";

import { Box, Flex, Icon, Text, useColorModeValue } from "@chakra-ui/react";
import BarChart from "components/charts/BarChart";

import Card from "components/card/Card.js";
import {
  barChartDataDailyTraffic,
  barChartOptionsDailyTraffic,
} from "views/admin/op031/variables/charts";
import {nombreOperationListLastSevenDays} from "services/LOT/op031";

export default function DailyTraffic(props) {
  const { ...rest } = props;

  const textColor = useColorModeValue("secondaryGray.900", "white");
  const [nombreOperation, setNombreOperation] = React.useState(null);
  React.useEffect(() => {
    fetchNombreOperation();
  }, []);
  const fetchNombreOperation = async () => {
    try {
      const nombreOperationList = await nombreOperationListLastSevenDays();
      var nombreOperation = 0;
      for (let i = 0; i < nombreOperationList.length; i++) {
        nombreOperation += nombreOperationList[i];
      }
      setNombreOperation(nombreOperation);
    } catch (error) {
      console.error("Error fetching nombreOperation:", error);
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
              {nombreOperation}
            </Text>
            <Text
              ms='6px'
              color='secondaryGray.600'
              fontSize='sm'
              fontWeight='500'>
              Opérations
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
