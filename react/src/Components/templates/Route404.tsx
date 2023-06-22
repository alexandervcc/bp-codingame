import React from "react";

const Route404 = () => {
  const queryParams = new URLSearchParams(window.location.search);
  const mensaje = queryParams.get("message") as string;
  return (
    <div>
      <h1>404</h1>
      <h2>Error: {mensaje}</h2>
    </div>
  );
};

export default Route404;
