import { Link } from 'react-router-dom';
import styled from 'styled-components';

const Bar = styled.header`
  background: linear-gradient(135deg, #89f7fe 0%, #66a6ff 100%);
  padding: 1rem;
  display: flex;
  justify-content: space-between;
`;

const Nav = styled.nav`
  a {
    margin: 0 1rem;
    color: white;
    text-decoration: none;
    font-weight: bold;
  }
`;

export default function Header() {
  return (
    <Bar>
      <h2 style={{ margin: 0, color: 'white' }}>My App</h2>
      <Nav>
        <Link to="/">Home</Link>
        <Link to="/data">Data Table</Link>
      </Nav>
    </Bar>
  );
}
