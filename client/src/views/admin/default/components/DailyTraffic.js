import React from "react";

import { Box, Flex, Text, useColorModeValue } from "@chakra-ui/react";
import BarChart from "components/charts/BarChart";

import Card from "components/card/Card.js";
import {
  barChartDataDailyTraffic,
  barChartOptionsDailyTraffic,
} from "../variables/charts";
import { opLastSixDays010 } from "services/LOT/op010";
import { opLastSixDays012 } from "services/LOT/op012";
import { opLastSixDays013 } from "services/LOT/op013";
import { opLastSixDays020 } from "services/LOT/op020";
import { opLastSixDays031 } from "services/LOT/op031";
import { opLastSixDays060 } from "services/LOT/op060";
import { opLastSixDays120 } from "services/LOT/rej120";
import { opLastSixDays131 } from "services/LOT/rej131";
import { opLastSixDays160 } from "services/LOT/rej160";

export default function DailyTraffic(props) {
  const { ...rest } = props;
  const [op, setOp] = React.useState(null);
  React.useEffect(() => {
    fetchOp();
  }, []);

  const fetchOp = async () =>{
    const op010 = (await opLastSixDays010()).reduce((sum, currentItem) => sum + currentItem, 0);
    const op012 = (await opLastSixDays012()).reduce((sum, currentItem) => sum + currentItem, 0);
    const op013 = (await opLastSixDays013()).reduce((sum, currentItem) => sum + currentItem, 0);
    const op020 = (await opLastSixDays020()).reduce((sum, currentItem) => sum + currentItem, 0);
    const op031 = (await opLastSixDays031()).reduce((sum, currentItem) => sum + currentItem, 0);
    const op060 = (await opLastSixDays060()).reduce((sum, currentItem) => sum + currentItem, 0);
    const rej120 = (await opLastSixDays120()).reduce((sum, currentItem) => sum + currentItem, 0);
    const rej131 = (await opLastSixDays131()).reduce((sum, currentItem) => sum + currentItem, 0);
    const rej160 = (await opLastSixDays160()).reduce((sum, currentItem) => sum + currentItem, 0);
    setOp(op010 + op012 + op013 + op020 + op031 + op060 + rej120 + rej131 + rej160)
  }

  const textColor = useColorModeValue("secondaryGray.900", "white");
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
              Nombre LOT 7 derniers jours :
            </Text>
          </Flex>
          <Flex align='end'>
            <Text
              color={textColor}
              fontSize='34px'
              fontWeight='700'
              lineHeight='100%'>
              {op}
            </Text>
            <Text
              ms='6px'
              color='secondaryGray.600'
              fontSize='sm'
              fontWeight='500'>
              Opérations / Rejets
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
 