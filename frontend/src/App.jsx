import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useState } from 'react';
import { ThemeProvider, createGlobalStyle } from 'styled-components';
import Header from './components/Header.jsx';
import Home from './components/Home.jsx';
import DataTable from './components/DataTable.jsx';
import './App.css';

const GlobalStyle = createGlobalStyle`
  body {
    display: grid;
    grid-template-rows: auto 1fr;
    place-items: center;
    background-color: ${props => props.theme.body};
    color: ${props => props.theme.text};
    font-family: system-ui, sans-serif;
    transition: background-color 0.3s ease;
  }
`;

const lightTheme = {
  body: '#ffffff',
  text: '#213547',
  header: 'linear-gradient(135deg, #89f7fe 0%, #66a6ff 100%)',
  primary: '#66a6ff'
};

const darkTheme = {
  body: '#242424',
  text: '#ffffff',
  header: 'linear-gradient(135deg, #141e30 0%, #243b55 100%)',
  primary: '#243b55'
};

export default function App() {
  const [themeName, setThemeName] = useState('light');
  const theme = themeName === 'light' ? lightTheme : darkTheme;
  const toggleTheme = () => setThemeName(t => (t === 'light' ? 'dark' : 'light'));

  return (
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <BrowserRouter>
        <Header onToggleTheme={toggleTheme} />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/data" element={<DataTable />} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
}
