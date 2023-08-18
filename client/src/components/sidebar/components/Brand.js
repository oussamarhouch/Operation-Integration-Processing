import React from "react";

import { Flex, useColorModeValue } from "@chakra-ui/react";

import { AttijariLogo } from "components/icons/Icons";
import { HSeparator } from "components/separator/Separator";

export function SidebarBrand() {
  let logoColor = useColorModeValue("navy.700", "white");

  return (
    <Flex align='center' direction='column'>
      <AttijariLogo h='200px' w='350px' mt='-25px' color={logoColor} />
      <HSeparator mb='20px' />
    </Flex>
  );
}

export default SidebarBrand;
