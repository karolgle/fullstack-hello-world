import styled from 'styled-components';

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: calc(100vh - 60px);
  background: linear-gradient(135deg, #f6d365 0%, #fda085 100%);
  color: #fff;
  text-align: center;
`;

const Button = styled.button`
  background: #fff;
  color: #333;
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
