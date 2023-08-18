import {
	Flex,
	Icon,
	Menu,
	MenuButton,
	Text,
	useColorModeValue
} from '@chakra-ui/react';

import { SearchBar } from 'components/navbar/searchBar/SearchBar';
import { SidebarResponsive } from 'components/sidebar/Sidebar';
import PropTypes from 'prop-types';
import React from 'react';
import { FaEthereum } from 'react-icons/fa';
import routes from 'routes.js';
import { useHistory } from "react-router-dom";
import { MdLogout } from 'react-icons/md';

export default function HeaderLinks(props) {
	const history = useHistory();

  const handleLogout = () => {
    localStorage.removeItem("token");
    history.push("/auth");
  };
	const { secondary } = props;
	const navbarIcon = useColorModeValue('gray.400', 'white');
	let menuBg = useColorModeValue('white', 'navy.800');
	const ethColor = useColorModeValue('gray.700', 'white');
	const ethBg = useColorModeValue('secondaryGray.300', 'navy.900');
	const ethBox = useColorModeValue('white', 'navy.800');
	const shadow = useColorModeValue(
		'14px 17px 40px 4px rgba(112, 144, 176, 0.18)',
		'14px 17px 40px 4px rgba(112, 144, 176, 0.06)'
	);
	return (
		<Flex
			w={{ sm: '100%', md: 'auto' }}
			alignItems="center"
			flexDirection="row"
			bg={menuBg}
			flexWrap={secondary ? { base: 'wrap', md: 'nowrap' } : 'unset'}
			p="10px"
			borderRadius="30px"
			boxShadow={shadow}>
			<SearchBar mb={secondary ? { base: '10px', md: 'unset' } : 'unset'} me="10px" borderRadius="30px" />
			<Flex
				bg={ethBg}
				display={secondary ? 'flex' : 'none'}
				borderRadius="30px"
				ms="auto"
				p="6px"
				align="center"
				me="6px">
				<Flex align="center" justify="center" bg={ethBox} h="29px" w="29px" borderRadius="30px" me="7px">
					<Icon color={ethColor} w="9px" h="14px" as={FaEthereum} />
				</Flex>
				<Text w="max-content" color={ethColor} fontSize="sm" fontWeight="700" me="6px">
					1,924
					<Text as="span" display={{ base: 'none', md: 'unset' }}>
						{' '}
						ETH
					</Text>
				</Text>
			</Flex>
			<SidebarResponsive routes={routes} />
			

			<Menu>
      <MenuButton p="0px" onClick={handleLogout} _hover={{ cursor: "pointer" }}>
        <Icon mt="6px" as={MdLogout} color={navbarIcon} w="20px" h="20px" me="20px" />
      </MenuButton>
    </Menu>
		</Flex>
	);
}

HeaderLinks.propTypes = {
	variant: PropTypes.string,
	fixed: PropTypes.bool,
	secondary: PropTypes.bool,
	onOpen: PropTypes.func
};
