package pages.pyt.accesosrapidos.beneficiariosinscritos;

import microservicios.beneficiarios.internacionales.MsConsultarCodigoInternacional;
import pages.PoAccionesRepetitivas;

/**
 * @Author: Carlos A. Loyola Tejeda
 * @Date 18/02/2024 12:42 a. m.
 * @Email: carlos_loyola@bhd.com.do
 */
public class BeneficiariosInscritosLogic {

    private final PoBeneficiariosPinPesos pinPesos;
    private final PoBeneficiariosNacionales nacionales;
    private final PoBeneficiariosInternacionales internacionales;

    private BeneficiariosInscritosLogic(){
        pinPesos = PoBeneficiariosPinPesos.getNewPagina();
        nacionales = PoBeneficiariosNacionales.getNewPagina();
        internacionales = PoBeneficiariosInternacionales.getNewPagina();
    }

    public static BeneficiariosInscritosLogic getLogica(){
        return new BeneficiariosInscritosLogic();
    }

    /** <h3> Permite agregar un beneficiario nacional a: BANCO MULTIPLE BHD, S.A y BHDIB Panamá. </h3>
     * @param usuarioActual Usuario actual desde el cual se esta agregando el beneficiario.
     * @param nombreBanco  Obligatorio - Permite seleccionar el nombre del banco destino: BANCO MULTIPLE BHD, S.A o BHDIB Panamá.
     * @param numProducto  Obligatorio - Permite llenar el campo numero de producto.
     * @param alias Opcional - Permite asignar un alias al beneficiario.
     * @param correo Opcional - Permite llenar el correo del beneficiario.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     * @return El beneficiario ha sido guardado correctamente
     */
    public String agregarBeneficiarioNacionalBHDPanama(String usuarioActual, String nombreBanco, String numProducto, String alias, String correo, boolean crearReporte){
        nacionales.irAPantallaListaBeneficiarios(crearReporte);
        nacionales.pantallaNuevoBeneficiarioBHDPanama(nombreBanco, numProducto, alias, correo, crearReporte);
        String msg = PoAccionesRepetitivas.getNewAccionesRepetitivasPage().guardarBeneficiarioCorrectamente("1111", crearReporte);
        nacionales.verificarBeneficiarioBHDPanama(usuarioActual,numProducto,crearReporte);
        return msg;
    }

    /** <h3> Permite agregar un beneficiario nacional de otros bancos. </h3>
     * @param usuarioActual Usuario actual desde el cual se agregara el beneficiario.
     * @param nombreBanco Obligatorio - Permite seleccionar el nombre del banco destino.
     * @param tipoProducto  Obligatorio - Permite seleccionar el tipo de producto: Cuentas de Ahorro, Cuentas Corrientes, Tarjetas de Crédito y Préstamos.
     * @param numProducto Obligatorio - Permite llenar el campo numero de producto.
     * @param tipoDocumento Obligatorio - Permite seleccionar el tipo de documento.
     * @param numDocumento Obligatorio - Permite llenar el numero de documento.
     * @param nombre Permite llenar el nombre del beneficiario. Obligatorio con pasaporte y con cedula si falla la junta.
     * @param alias Opcional - Permite asignar un alias al beneficiario.
     * @param pais Permite seleccionar el país del beneficiario. Es obligatorio si es tipo documento pasaporte o si falla la junta.
     * @param genero Permite llenar el genero del beneficiario. Es obligatorio si es tipo documento pasaporte o si falla la junta.
     * @param correo Opcional - Permite llenar el correo del beneficiario.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     * @return El beneficiario ha sido guardado correctamente
     */
    public String agregarBeneficiarioNacionalDeOtrosBancos(String usuarioActual, String nombreBanco, String tipoProducto, String numProducto, String tipoDocumento, String numDocumento, String nombre, String alias, String pais, String genero, String correo, boolean crearReporte){
        nacionales.irAPantallaListaBeneficiarios(crearReporte);
        nacionales.pantallaNuevoBeneficiarioDeOtrosBancos(nombreBanco, tipoProducto, numProducto, tipoDocumento, numDocumento, nombre, alias, pais, genero, correo, crearReporte);
        String msg = PoAccionesRepetitivas.getNewAccionesRepetitivasPage().guardarBeneficiarioCorrectamente("1111", crearReporte);
        nacionales.verificarBeneficiarioDeOtrosBancos(usuarioActual,numProducto,crearReporte);
        return msg;
    }

    /** <h3> Permite actualizar un beneficiario nacional de los bancos: BANCO MULTIPLE BHD, S.A y BHDIB Panamá. </h3>
     * @param usuarioActual Usuario actual desde el cual se actualizara el beneficiario.
     * @param beneficiarioAEditar Número de producto del beneficiario que se va a editar.
     * @param alias Opcional - Permite actualizar el alias del beneficiario.
     * @param correo Opcional - Permite actualizar el correo del beneficiario.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     * @return El beneficiario ha sido guardado correctamente
     */
    public String actualizarBeneficiarioNacionalBHDPanama(String usuarioActual, String beneficiarioAEditar, String alias, String correo, boolean crearReporte){
        nacionales.pantallaActualizarBeneficiario(usuarioActual, beneficiarioAEditar, alias, correo, crearReporte);
        String message = PoAccionesRepetitivas.getNewAccionesRepetitivasPage().guardarBeneficiarioCorrectamente("1111", crearReporte);
        nacionales.verificarBeneficiarioBHDPanama( usuarioActual, beneficiarioAEditar, crearReporte);
        return message;
    }

    /** <h3> Permite actualizar un beneficiario nacional de otros bancos. </h3>
     * @param usuarioActual Usuario actual desde el cual se actualizara el beneficiario.
     * @param beneficiarioAEditar Número de producto del beneficiario que se va a editar.
     * @param nombre Opcional - Permite actualizar el nombre del beneficiario.
     * @param alias Opcional - Permite actualizar el alias del beneficiario.
     * @param pais Opcional - Permite actualizar el pais del beneficiario.
     * @param genero Opcional - Permite actualizar el genero del beneficiario.
     * @param correo Opcional - Permite actualizar el correo del beneficiario.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     * @return El beneficiario ha sido guardado correctamente
     */
    public String actualizarBeneficiarioNacionalDeOtrosBancos(String usuarioActual, String beneficiarioAEditar, String alias, String pais, String genero, String correo, boolean crearReporte){
        nacionales.pantallaActualizarBeneficiarioDeOtrosBancos(usuarioActual, beneficiarioAEditar, alias,pais,genero,correo, crearReporte);
        String message = PoAccionesRepetitivas.getNewAccionesRepetitivasPage().guardarBeneficiarioCorrectamente("1111", crearReporte);
        nacionales.verificarBeneficiarioBHDPanama( usuarioActual, beneficiarioAEditar, crearReporte);
        return message;
    }

    /** <h3> Este método permite eliminar un beneficiario Nacional BHD y otros bancos. </h3>
     * @param usuarioActual Usuario actual desde el cual se eliminara el beneficiario.
     * @param cuentaBeneficiarioAEliminar Número de cuenta del beneficiario que se va a eliminar.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     * @return No se encontraron registros para mostrar
     */
    public String eliminarBeneficiarioNacional(String usuarioActual, String cuentaBeneficiarioAEliminar, boolean crearReporte){
        nacionales.modalEliminarBeneficiario( usuarioActual, cuentaBeneficiarioAEliminar, crearReporte);
        return PoAccionesRepetitivas.getNewAccionesRepetitivasPage().confirmarEliminarBeneficiario(crearReporte);
    }




    /** <h3> Permite agregar un beneficiario internacional. </h3>
     * @param nombre Obligatorio. Permite llenar el nombre del beneficiario.
     * @param numIdentificacion Opcional. Permite llenar el numIdentificacion del beneficiario.
     * @param pais Obligatorio. Permite llenar el pais del beneficiario.
     * @param calle Obligatorio. Permite llenar la calle del beneficiario.
     * @param correo Opcional. Permite llenar el correo del beneficiario.
     * @param numIban Obligatorio. Permite llenar el numProductoIBAN del beneficiario.
     * @param moneda Obligatorio. Permite seleccionar la moneda: Dólares, Euros.
     * @param tipoCodigo Obligatorio. Permite seleccionar el tipo de código: SWIFT o ABA.
     * @param codigo Obligatorio. Permite ingresar el código de tipo de código ingresado anteriormente.
     * @param usarBancoIntermediario Opcional. Permite agregar un banco intermediario si es necesario.
     * @param numIbanIntermediario Opcional. Permite ingresar el Numero Iban del banco intermediario.
     * @param tipoCodIntermediario Opcional. Permite seleccionar el tipo de código: SWIFT o ABA, del banco Intermediario, en caso de que se haya seleccionado agregar banco intermediario en la opción anterior.
     * @param codIntermediario Opcional. Permite ingresar el código para el banco intermediario.
     * @param destino Obligatorio. Permite seleccionar el destino.
     * @param clasificacion Obligatorio. Permite seleccionar el clasificacion.
     * @param proposito Obligatorio. Permite seleccionar el proposito.
     * @param crearReporte
     * @return El beneficiario ha sido guardado correctamente
     */
    public String agregarBeneficiarioInternacional(String usuarioActual,String nombre, String numIdentificacion, String pais, String calle, String correo, String numIban, String moneda, String tipoCodigo, String codigo, boolean usarBancoIntermediario, String numIbanIntermediario, String tipoCodIntermediario, String codIntermediario, String destino, String clasificacion, String proposito, boolean crearReporte){
        MsConsultarCodigoInternacional banco = new MsConsultarCodigoInternacional(usuarioActual).newQuery(tipoCodigo,codigo);
        MsConsultarCodigoInternacional bancoIntermediario = new MsConsultarCodigoInternacional(usuarioActual);
        if (usarBancoIntermediario){
            bancoIntermediario.newQuery(tipoCodIntermediario,codIntermediario);
        }
        internacionales.irAPantallaListaBeneficiarios(crearReporte);
        internacionales.pantallaNuevoBeneficiario(nombre, numIdentificacion, pais, calle, correo, numIban, moneda, tipoCodigo, codigo, usarBancoIntermediario, numIbanIntermediario, tipoCodIntermediario, codIntermediario, destino, clasificacion, proposito, crearReporte);

        internacionales.verificarVisualizacionCodigoBanco(banco, bancoIntermediario, usarBancoIntermediario);
        String msg = PoAccionesRepetitivas.getNewAccionesRepetitivasPage().guardarBeneficiarioCorrectamente("1111", crearReporte);
        internacionales.verificarVisualizacionBeneficiario( usuarioActual, numIban, crearReporte);
        return msg;
    }

    /** <h3> Permite verificar que no se agregue un beneficiario ya existente. </h3>
     * @param nombre Obligatorio. Permite llenar el nombre del beneficiario.
     * @param numIdentificacion Obligatorio. Permite llenar el numIdentificacion del beneficiario.
     * @param pais Obligatorio. Permite llenar el pais del beneficiario.
     * @param calle Obligatorio. Permite llenar la calle del beneficiario.
     * @param correo Opcional. Permite llenar el correo del beneficiario.
     * @param numIban Obligatorio. Permite llenar el numProductoIBAN del beneficiario.
     * @param moneda Obligatorio. Permite seleccionar la moneda: Dólares, Euros.
     * @param tipoCodigo Permite seleccionar el tipo de código: SWIFT o ABA.
     * @param codigo Permite ingresar el código de tipo de código ingresado anteriormente.
     * @param usarBancoIntermediario Opcional. Permite agregar un banco intermediario si es necesario.
     * @param numIbanIntermediario Permite ingresar el Numero Iban del banco intermediario.
     * @param tipoCodIntermediario Permite seleccionar el tipo de código: SWIFT o ABA, del banco Intermediario, en caso de que se haya seleccionado agregar banco intermediario en la opción anterior.
     * @param codIntermediario Permite ingresar el código para el banco intermediario.
     * @param destino Obligatorio. Permite seleccionar el destino.
     * @param clasificacion Obligatorio. Permite seleccionar el clasificacion.
     * @param proposito Obligatorio. Permite seleccionar el proposito.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return Este beneficiario ha sido inscrito anteriormente
     */
    public String agregarBeneficiarioInternacionalExistente(String usuarioActual,String nombre, String numIdentificacion, String pais, String calle, String correo, String numIban, String moneda, String tipoCodigo, String codigo,boolean usarBancoIntermediario, String numIbanIntermediario, String tipoCodIntermediario, String codIntermediario, String destino, String clasificacion, String proposito,boolean crearReporte){
        internacionales.irAPantallaListaBeneficiarios(crearReporte);
        internacionales.pantallaNuevoBeneficiario(nombre, numIdentificacion, pais, calle, correo, numIban, moneda, tipoCodigo, codigo, usarBancoIntermediario, numIbanIntermediario, tipoCodIntermediario, codIntermediario, destino, clasificacion, proposito, crearReporte);
        String msg = PoAccionesRepetitivas.getNewAccionesRepetitivasPage().guardarBeneficiarioExistente("1111", crearReporte);
        internacionales.verificarVisualizacionBeneficiario( usuarioActual, numIban, crearReporte);
        return msg;
    }

    /** <h3> Permite actualizar un beneficiario internacional. </h3>
     * @param usuarioActual
     * @param numIbanBeneficiarioAEditar numIban del beneficiario a Actualizar.
     * @param numIdentificacion Obligatorio. Permite llenar el numero de Identificacion del beneficiario.
     * @param pais Opcional. Permite llenar el pais del beneficiario.
     * @param calle Opcional. Permite llenar la calle del beneficiario.
     * @param correo Opcional. Permite llenar el correo del beneficiario.
     * @param moneda Opcional. Permite seleccionar la moneda.
     * @param destino Opcional. Permite seleccionar el destino.
     * @param clasificacion Opcional. Permite seleccionar el clasificacion.
     * @param proposito Opcional. Permite seleccionar el proposito.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return El beneficiario ha sido guardado correctamente
     */
    public String actualizarBeneficiarioInternacional(String usuarioActual, String numIbanBeneficiarioAEditar, String numIdentificacion, String pais, String calle, String correo, String moneda, String destino, String clasificacion, String proposito, boolean crearReporte){
        internacionales.pantallaEditarBeneficiario(numIbanBeneficiarioAEditar, numIdentificacion, pais, calle, correo, moneda, destino, clasificacion, proposito, crearReporte);
        String msg = PoAccionesRepetitivas.getNewAccionesRepetitivasPage().guardarBeneficiarioCorrectamente("1111",true);
        internacionales.verificarVisualizacionBeneficiario( usuarioActual, numIbanBeneficiarioAEditar, crearReporte);
        return msg;
    }

    /** <h3> Permite eliminar un beneficiario internacional. </h3>
     * @param numIbanDeBeneficiarioAEliminar Numero iban del beneficiario que se va a eliminar.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return No se encontraron registros para mostrar
     */
    public String eliminarBeneficiarioInternacional(String numIbanDeBeneficiarioAEliminar, boolean crearReporte){
        internacionales.pantallaEliminarBeneficiario( numIbanDeBeneficiarioAEliminar, crearReporte);
        return PoAccionesRepetitivas.getNewAccionesRepetitivasPage().confirmarEliminarBeneficiario( crearReporte );
    }

    


    /** <h3> Este método permite agregar un nuevo beneficiario PIN Pesos desde la pantalla de beneficiarios PIN Pesos. </h3>
     * @param usuarioActual Usuario actual desde el cual se agregara el beneficiario.
     * @param alias <a href='#'>Opcional</a> - Permite asignar un alias al beneficiario.
     * @param telefono <a href='#'>Obligatorio</a> - Permite agregar el número de teléfono del beneficiario.
     * @param tipoDocumento <a href='#'>Obligatorio</a> - Permite seleccionar el tipo de documento: Cédula, RNC
     * @param numeroDocumento <a href='#'>Obligatorio</a> - Permite llenar el numero de documento.
     * @param nombre Obligatorio, con tipo de documento pasaporte o si falla la Junta.
     * @param pais Obligatorio, con tipo documento pasaporte o si falla la junta. Permite llenar el pais del beneficiario.
     * @param genero Obligatorio, con tipo documento pasaporte o si falla la junta. Permite llenar el genero del beneficiario.
     * @param crearReporte Permite crear o no reporte de este paso a paso.
     * @return El beneficiario ha sido guardado correctamente
     */
    public String agregarBeneficiarioPinPesos(String usuarioActual, String alias, String telefono, String tipoDocumento, String numeroDocumento, String nombre, String pais, String genero, boolean crearReporte){
        pinPesos.irAPantallaListaBeneficiarios( crearReporte );
        pinPesos.irAPantallaNuevoBeneficiario( alias, telefono, tipoDocumento, numeroDocumento, nombre, pais, genero, crearReporte );
        String message = PoAccionesRepetitivas.getNewAccionesRepetitivasPage().guardarBeneficiarioCorrectamente("1111",true);
        pinPesos.consultarMsVerificarVisualizacionBeneficiario( usuarioActual, telefono, crearReporte );
        return message;
    }

    /** <h3> Este metodo permite actualizar un beneficiario pin pesos.</h3>
     * @param usuarioActual Obligatorio - Usuario actual desde el cual se esta iniciando sesión.
     * @param telefBeneficiarioActualizar Obligatorio - Teléfono del beneficiario que se va actualizar.
     * @param alias Opcional - Permite actualizar el alias del beneficiario.
     * @param nombre Opcional - Permite actualizar el nombre del beneficiario.
     * @param pais Opcional - Permite actualizar el pais del beneficiario.
     * @param genero Opcional - Permite actualizar el genero del beneficiario.
     * @param crearReporte Permite crear o no reporte de este proceso.
     * @return El beneficiario ha sido guardado correctamente
     */
    public String actualizarBeneficiarioPinPesos(String usuarioActual,String telefBeneficiarioActualizar, String alias, String nombre, String pais, String genero, boolean crearReporte){
        pinPesos.irAPantallaEditarBeneficiario( telefBeneficiarioActualizar, alias, nombre, pais, genero, crearReporte);
        String message = PoAccionesRepetitivas.getNewAccionesRepetitivasPage().guardarBeneficiarioCorrectamente("1111",true);
        pinPesos.consultarMsVerificarVisualizacionBeneficiario( usuarioActual, telefBeneficiarioActualizar, crearReporte );
        return message;
    }

    public String eliminarBeneficiarioPinPesos(String usuario, String beneficiario, boolean crearReporte){
        pinPesos.modalEliminarBeneficiario( usuario, beneficiario, crearReporte);
        PoAccionesRepetitivas.getNewAccionesRepetitivasPage().confirmarEliminarBeneficiarioVoid( crearReporte );
        return pinPesos.verificarBeneficiarioNoExistente(beneficiario,crearReporte);
    }

}
