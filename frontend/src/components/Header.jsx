import { Link } from 'react-router-dom';
import styled from 'styled-components';

const Bar = styled.header`
  background: ${props => props.theme.header};
  padding: 1rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
`;

const Nav = styled.nav`
  display: flex;
  align-items: center;
  a {
    margin: 0 1rem;
    color: white;
    text-decoration: none;
    font-weight: bold;
  }
`;

const ToggleButton = styled.button`
  margin-left: 1rem;
  background: transparent;
  border: 2px solid white;
  color: white;
  padding: 0.3rem 0.6rem;
  border-radius: 4px;
  cursor: pointer;
`;

export default function Header({ onToggleTheme }) {
  return (
    <Bar>
      <h2 style={{ margin: 0, color: 'white' }}>My App</h2>
      <Nav>
        <Link to="/">Home</Link>
        <Link to="/data">Data Table</Link>
        <ToggleButton onClick={onToggleTheme}>Toggle</ToggleButton>
      </Nav>
    </Bar>
  );
}
