import { useEffect, useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";

const Email = () => {
  const params = useParams();
  const [cliente, setCliente] = useState();
  const getCliente = async () => {
    const aut = JSON.parse(localStorage.getItem("token"));
    console.log(aut.accessToken);
    try {
      const risp = await fetch(`http://localhost:3001/clienti/${params.id}`, {
        method: "GET",
        headers: {
          "content-type": "application/json",
          Authorization: `Bearer ${aut.accessToken}`,
        },
      });
      if (risp.ok) {
        const data = await risp.json();
        console.log(data);
        setCliente(data);
      }
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    console.log("mount");
    getCliente();
  }, []);

  return (
    <>
      {cliente && (
        <Container className="mt-5">
          <Form>
            <Form.Group className="mb-3">
              <Form.Label>Indirizzo email</Form.Label>
              <Form.Control type="email" disabled placeholder={cliente.emailContatto} />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Oggetto</Form.Label>
              <Form.Control type="email" placeholder="Oggetto" className="mb-3" />
              <Form.Label>Contenuto</Form.Label>
              <Form.Control as="textarea" rows={6} placeholder="Contenuto" className="mb-3" />
              <div className="d-flex justify-content-between">
                <Link to={"/utenti"} className="m-0">
                  <Button variant="secondary">Indietro</Button>
                </Link>
                <Button type="submit" variant="primary" className="ms-3">
                  Invia
                </Button>
              </div>
            </Form.Group>
          </Form>
        </Container>
      )}
    </>
  );
};

export default Email;
