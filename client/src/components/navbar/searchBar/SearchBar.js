import React, { useState } from "react";
import {
  IconButton,
  Input,
  InputGroup,
  InputLeftElement,
  useColorModeValue,
} from "@chakra-ui/react";
import { SearchIcon } from "@chakra-ui/icons";

export function SearchBar(props) {
  const {
    variant,
    background,
    children,
    placeholder,
    borderRadius,
    ...rest
  } = props;

  const searchIconColor = useColorModeValue("gray.700", "white");
  const inputBg = useColorModeValue("secondaryGray.300", "navy.900");
  const inputText = useColorModeValue("gray.700", "gray.100");

  const [searchQuery, setSearchQuery] = useState("");

  const highlightSearch = (text) => {
    if (!searchQuery) return text;

    if (typeof text !== "string") {
      return text;
    }

    const regex = new RegExp(`(${searchQuery})`, "gi");
    return text.split(regex).map((part, index) => {
      if (part.toLowerCase() === searchQuery.toLowerCase()) {
        return <mark key={index}>{part}</mark>;
      } else {
        return part;
      }
    });
  };

  return (
    <InputGroup w={{ base: "100%", md: "200px" }} {...rest}>
      <InputLeftElement
        children={
          <IconButton
            bg="inherit"
            borderRadius="inherit"
            _hover="none"
            _active={{
              bg: "inherit",
              transform: "none",
              borderColor: "transparent",
            }}
            _focus={{
              boxShadow: "none",
            }}
            icon={<SearchIcon color={searchIconColor} w="15px" h="15px" />}
          ></IconButton>
        }
      />
      <div style={{ position: "relative" }}>
        <Input
          variant="search"
          fontSize="sm"
          bg={background ? background : inputBg}
          color={inputText}
          fontWeight="500"
          _placeholder={{ color: "gray.400", fontSize: "14px" }}
          borderRadius={borderRadius ? borderRadius : "30px"}
          placeholder={placeholder ? placeholder : "Search..."}
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          style={{ paddingLeft: "30px" }}
        />
        <div
          style={{
            position: "absolute",
            top: "50%",
            left: "5px",
            transform: "translateY(-50%)",
          }}
        >
          {highlightSearch(children)}
        </div>
      </div>
    </InputGroup>
  );
}
