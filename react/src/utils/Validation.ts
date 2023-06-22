export const isNumber = (str: string): boolean => {
  if (typeof str !== "string") {
    return false;
  }

  if (str.trim() === "") {
    return false;
  }

  return !Number.isNaN(Number(str));
};

export const checkValidArguments_MovementsSearch = (
  nombreCliente: string,
  initialDate: string,
  finalDate: string
):boolean => {
  if (nombreCliente.length === 0 || !nombreCliente) {
    alert("Cliente a buscar vacio.");
    return false;
  }
  if (!initialDate || !finalDate) {
    alert("Fechas sin escoger.");
    return false;
  }
  return true;
};
