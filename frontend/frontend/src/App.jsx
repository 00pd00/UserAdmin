import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [users, setUsers] = useState([]);
  const [form, setForm] = useState({ name: '', id: null });
  const [isEditing, setIsEditing] = useState(false);

  // Load users from backend
  const loadUsers = () => {
    fetch('http://localhost:8080/api/users')
      .then(res => res.json())
      .then(data => setUsers(data));
  };

  useEffect(() => {
    loadUsers();
  }, []);

  // Add or update user
  const handleSubmit = (e) => {
    e.preventDefault();
    const method = isEditing ? 'PUT' : 'POST';
    const url = isEditing
      ? `http://localhost:8080/api/users/${form.id}`
      : `http://localhost:8080/api/users`;

    fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name: form.name })
    }).then(() => {
      setForm({ name: '', id: null });
      setIsEditing(false);
      loadUsers();
    });
  };

  // Edit user
  const handleEdit = (user) => {
    setForm({ name: user.name, id: user.id });
    setIsEditing(true);
  };

  // Delete user
  const handleDelete = (id) => {
    fetch(`http://localhost:8080/api/users/${id}`, {
      method: 'DELETE'
    }).then(() => loadUsers());
  };

  return (
    <div className="App">
      <h2>User Management</h2>
      <form onSubmit={handleSubmit}>
        <input
          value={form.name}
          onChange={e => setForm({ ...form, name: e.target.value })}
          placeholder="Enter name"
        />
        <button type="submit">{isEditing ? 'Update' : 'Add'}</button>
      </form>

      <ul>
        {users.map(user => (
          <li key={user.id}>
            {user.name} &nbsp;
            <button onClick={() => handleEdit(user)}>Edit</button>
            <button onClick={() => handleDelete(user.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
