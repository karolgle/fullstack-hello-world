import { useEffect, useState } from 'react';
import styled from 'styled-components';

const Wrapper = styled.div`
  padding: 2rem;
`;

const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
  td, th { border: 1px solid #ccc; padding: 0.5rem; }
`;

export default function DataTable() {
  const [rows, setRows] = useState([]);
  const [form, setForm] = useState({ name: '', value: '' });
  const [error, setError] = useState(null);

  const load = () => {
    fetch('/api/data')
      .then(res => res.json())
      .then(setRows)
      .catch(() => setError('Failed to load data'));
  };

  useEffect(() => {
    load();
  }, []);

  const addRow = e => {
    e.preventDefault();
    fetch('/api/data', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name: form.name, value: Number(form.value) })
    }).then(load);
  };

  const updateRow = (id, data) => {
    fetch(`/api/data/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data)
    }).then(load);
  };

  const deleteRow = id => {
    fetch(`/api/data/${id}`, { method: 'DELETE' }).then(load);
  };

  return (
    <Wrapper>
      <h1>Data Table</h1>
      {error && <p>{error}</p>}
      <form onSubmit={addRow} style={{ marginBottom: '1rem' }}>
        <input
          placeholder="Name"
          value={form.name}
          onChange={e => setForm({ ...form, name: e.target.value })}
          required
        />
        <input
          placeholder="Value"
          type="number"
          value={form.value}
          onChange={e => setForm({ ...form, value: e.target.value })}
          required
        />
        <button type="submit">Add</button>
      </form>
      <Table>
        <thead>
          <tr><th>ID</th><th>Name</th><th>Value</th><th>Actions</th></tr>
        </thead>
        <tbody>
          {rows.map(r => (
            <tr key={r.id}>
              <td>{r.id}</td>
              <td>
                <input defaultValue={r.name} onBlur={e => updateRow(r.id, { ...r, name: e.target.value })} />
              </td>
              <td>
                <input type="number" defaultValue={r.value} onBlur={e => updateRow(r.id, { ...r, value: Number(e.target.value) })} />
              </td>
              <td><button onClick={() => deleteRow(r.id)}>Delete</button></td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Wrapper>
  );
}
