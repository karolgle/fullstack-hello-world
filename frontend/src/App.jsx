import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './components/Header.jsx';
import Home from './components/Home.jsx';
import DataTable from './components/DataTable.jsx';
import './App.css';

export default function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/data" element={<DataTable />} />
      </Routes>
    </BrowserRouter>
  );
}
