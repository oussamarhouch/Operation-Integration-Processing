import React from 'react';
import ReactDOM from 'react-dom';
import 'assets/css/App.css';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';
import AuthLayout from 'layouts/auth';
import AdminLayout from 'layouts/admin';
import { ChakraProvider } from '@chakra-ui/react';
import theme from 'theme/theme';
import { ThemeEditorProvider } from '@hypertheme-editor/chakra-ui';

const isAuthenticated = () => {
	return localStorage.getItem("token") !== null;
  };

ReactDOM.render(
	<ChakraProvider theme={theme}>
		<React.StrictMode>
			<ThemeEditorProvider>
				<BrowserRouter>
					<Switch>
						<Route path={`/auth`} component={AuthLayout} />
						<Route path={`/admin`} component={AdminLayout} />
						<Route
              path="/admin"
              render={() =>
                isAuthenticated() ? <AdminLayout /> : <Redirect to="/auth" />
              }
            />
            <Redirect from="/" to="/auth" />
					</Switch>
				</BrowserRouter>
			</ThemeEditorProvider>
		</React.StrictMode>
	</ChakraProvider>,
	document.getElementById('root')
);
