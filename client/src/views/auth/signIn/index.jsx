import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import {
  Box,
  Button,
  Flex,
  FormControl,
  FormLabel,
  Heading,
  Input,
  Text,
  useColorModeValue
} from "@chakra-ui/react";
import { Alert, AlertIcon, CloseButton } from "@chakra-ui/react";
import { HSeparator } from "components/separator/Separator";
import DefaultAuth from "layouts/auth/Default";
import login from "../../../services/signIn";

function SignIn() {
  const history = useHistory();
  const textColor = useColorModeValue("navy.700", "white");
  const textColorSecondary = "gray.400";
  const brandStars = useColorModeValue("brand.500", "brand.400");
  const [show, setShow] = useState(false);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleLogin = async () => {
    setIsLoading(true);
    setError(null);

    try {
      const token = await login(username, password);
      console.log("Login successful. Token:", token);
      history.push("/admin");
    } catch (error) {
      console.error("Login failed:", error.message);
      setError(error.message);
    }

    setIsLoading(false);
  };

  return (
    <DefaultAuth>
      <Flex
        maxW={{ base: "100%", md: "max-content" }}
        w="100%"
        mx="auto"
        my="auto"
        h="100%"
        alignItems="center"
        justifyContent="center"
        mb={{ base: "30px", md: "60px" }}
        px={{ base: "25px", md: "0px" }}
        mt={{ base: "40px", md: "14vh" }}
        flexDirection="column"
      >
        <Box me="auto">
          <Heading color={textColor} fontSize="36px" mb="10px">
            Sign In
          </Heading>
          <Text
            mb="36px"
            ms="4px"
            color={textColorSecondary}
            fontWeight="400"
            fontSize="md"
          >
            Enter the username and password provided to you to sign in
          </Text>
        </Box>
        <Flex
          zIndex="2"
          direction="column"
          w={{ base: "100%", md: "420px" }}
          maxW="100%"
          background="transparent"
          borderRadius="15px"
          mx={{ base: "auto", lg: "unset" }}
          me="auto"
          mb={{ base: "20px", md: "auto" }}
        >
          <Flex align="center" mb="25px">
            <HSeparator />
            <Text color="gray.400" mx="14px">
              Welcome
            </Text>
            <HSeparator />
          </Flex>
          <FormControl>
            <FormLabel
              display="flex"
              ms="4px"
              fontSize="sm"
              fontWeight="500"
              color={textColor}
              mb="8px"
            >
              Username<Text color={brandStars}>*</Text>
            </FormLabel>
            <Input
                isRequired={true}
                variant="auth"
                fontSize="sm"
                ms={{ base: "0px", md: "0px" }}
                type="text"
                placeholder="Enter your Username"
                mb="24px"
                fontWeight="500"
                size="lg"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <FormLabel
              ms="4px"
              fontSize="sm"
              fontWeight="500"
              color={textColor}
              display="flex"
            >
              Password<Text color={brandStars}>*</Text>
            </FormLabel>
            <Input
                isRequired={true}
                fontSize="sm"
                placeholder="Enter your Password"
                mb="24px"
                size="lg"
                type={show ? "text" : "password"}
                variant="auth"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
              
            <Flex justifyContent="space-between" align="center" mb="24px">

            </Flex>
            <Button
              fontSize="sm"
              variant="brand"
              fontWeight="500"
              w="100%"
              h="50"
              mb="24px"
              onClick={handleLogin}
              isLoading={isLoading}
            >
              Sign In
            </Button>
          </FormControl>
          {error && (
        <Alert status="error" mt="4" borderRadius="md">
          <AlertIcon />
          {error}
          <CloseButton
            position="absolute"
            right="8px"
            top="8px"
            onClick={() => setError(null)}
          />
        </Alert>
      )}
          <Flex
            flexDirection="column"
            justifyContent="center"
            alignItems="start"
            maxW="100%"
            mt="0px"
          ></Flex>
        </Flex>
      </Flex>
    </DefaultAuth>
  );
}

export default SignIn;
