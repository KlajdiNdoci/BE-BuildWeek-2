import { Col, ListGroup, Row } from "react-bootstrap";

const Lista = ({ listaClienti }) => {
  return (
    listaClienti &&
    listaClienti.map((elem, index) => (
      <ListGroup.Item key={index}>
        <Row xs={4} className="d-flex justify-content-between align-items-center">
          <Col className="text-start">
            <div>{elem.nomeContatto}</div>
          </Col>
          <Col className="text-start">
            <div>{elem.cognomeContatto}</div>
          </Col>
          <Col className="text-start">
            <div>{elem.telefonoContatto}</div>
          </Col>
        </Row>
      </ListGroup.Item>
    ))
  );
};

export default Lista;
