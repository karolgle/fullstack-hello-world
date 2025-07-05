import { useEffect, useState } from 'react';
import styled from 'styled-components';

const Wrapper = styled.div`
  display: grid;
  place-items: center;
  gap: 1rem;
  padding: 2rem;
`;

const Table = styled.table`
  width: 100%;
  max-width: 600px;
  border-collapse: collapse;
  td, th { border: 1px solid #ccc; padding: 0.5rem; }
`;

export default function DataTable() {
  const [rows, setRows] = useState([]);
  const [form, setForm] = useState({ label: '', value: '' });
  const [editingId, setEditingId] = useState(null);
  const [editData, setEditData] = useState({ label: '', value: '' });
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
      body: JSON.stringify({ label: form.label, value: form.value })
    }).then(() => {
      setForm({ label: '', value: '' });
      load();
    });
  };

  const updateRow = (id, data) => {
    fetch(`/api/data/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data)
    }).then(() => {
      setEditingId(null);
      load();
    });
  };

  const deleteRow = id => {
    fetch(`/api/data/${id}`, { method: 'DELETE' }).then(() => {
      setEditingId(null);
      load();
    });
  };

  return (
    <Wrapper>
      <h1>Data Table</h1>
      {error && <p>{error}</p>}
      <form onSubmit={addRow} style={{ marginBottom: '1rem' }}>
        <input
          placeholder="Label"
          value={form.label}
          onChange={e => setForm({ ...form, label: e.target.value })}
          required
        />
        <input
          placeholder="Value"
          value={form.value}
          onChange={e => setForm({ ...form, value: e.target.value })}
          required
        />
        <button type="submit">Add</button>
      </form>
      <Table>
        <thead>
          <tr><th>ID</th><th>Label</th><th>Value</th><th>Actions</th></tr>
        </thead>
        <tbody>
          {rows.map(r => (
            <tr key={r.id}>
              {editingId === r.id ? (
                <>
                  <td>{r.id}</td>
                  <td>
                    <input value={editData.label} onChange={e => setEditData({ ...editData, label: e.target.value })} />
                  </td>
                  <td>
                    <input value={editData.value} onChange={e => setEditData({ ...editData, value: e.target.value })} />
                  </td>
                  <td>
                    <button onClick={() => updateRow(r.id, editData)}>Save</button>
                    <button onClick={() => deleteRow(r.id)}>Delete</button>
                  </td>
                </>
              ) : (
                <>
                  <td>{r.id}</td>
                  <td>{r.label}</td>
                  <td>{r.value}</td>
                  <td>
                    <button onClick={() => { setEditingId(r.id); setEditData({ label: r.label, value: r.value }); }}>Edit</button>
                    <button onClick={() => deleteRow(r.id)}>Delete</button>
                  </td>
                </>
              )}
            </tr>
          ))}
        </tbody>
      </Table>
    </Wrapper>
  );
}
