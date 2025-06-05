// src/pages/SendEmail.js
import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Alert, Box, Typography } from '@mui/material';

const SendEmail = () => {
  const [form, setForm] = useState({
    toEmail: '',
    policyHolderName: '',
    policyNumber: ''
  });
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleChange = e => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async e => {
    e.preventDefault();
    setMessage('');
    setError('');
    try {
      const res = await axios.post('/api/notify/send-policy', null, {
        params: form
      });
      setMessage(res.data);
    } catch (err) {
      setError(err.response?.data || 'Failed to send email');
    }
  };

  return (
    <Box className="p-6 max-w-md mx-auto shadow-lg rounded bg-white mt-10">
      <Typography variant="h5" gutterBottom>Send Policy via Email</Typography>
      
      <form onSubmit={handleSubmit} className="space-y-4">
        <TextField
          name="toEmail"
          label="Recipient Email"
          fullWidth
          required
          onChange={handleChange}
        />
        <TextField
          name="policyHolderName"
          label="Policy Holder Name"
          fullWidth
          required
          onChange={handleChange}
        />
        <TextField
          name="policyNumber"
          label="Policy Number"
          fullWidth
          required
          onChange={handleChange}
        />
        <Button type="submit" variant="contained" color="primary" fullWidth>
          Send Email with PDF
        </Button>
      </form>

      {message && <Alert severity="success" className="mt-4">{message}</Alert>}
      {error && <Alert severity="error" className="mt-4">{error}</Alert>}
    </Box>
  );
};

export default SendEmail;
