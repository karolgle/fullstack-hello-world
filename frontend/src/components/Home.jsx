import styled from 'styled-components';

const Wrapper = styled.div`
  display: grid;
  place-items: center;
  gap: 1rem;
  text-align: center;
  padding: 2rem;
`;

const Button = styled.button`
  background: ${props => props.theme.primary};
  color: #fff;
  border: none;
  padding: 0.8rem 1.5rem;
  font-size: 1rem;
  border-radius: 4px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  cursor: pointer;
`;

export default function Home() {
  return (
    <Wrapper>
      <h1>Life is Beautiful</h1>
      <p>Embrace the wonders of nature and live vibrantly.</p>
      <Button>Learn More</Button>
    </Wrapper>
  );
}
