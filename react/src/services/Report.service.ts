export const downloadPDFMovementsReportByClient = async (
  clienteName: string,
  fechaInicio: string,
  fechaFin: string
): Promise<Response> => {
  const response = await fetch(
    `${process.env.REACT_APP_API_URL}/reporte/?clientName=${clienteName}&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`,
    {
      method: "GET",
    }
  );
  return response;
};

export const openPDFInNewTab = (pdfDocumentBlob: Blob) => {
  var pdfUrl = URL.createObjectURL(pdfDocumentBlob);
  window.open(pdfUrl);
};
