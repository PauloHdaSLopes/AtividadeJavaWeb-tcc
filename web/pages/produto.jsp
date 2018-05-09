<%
    String valid = request.getParameter("produtoInserido");
%>
<html>
    <head>
        <%@include file="../default/head.jsp" %>
    </head>
    <body class="nav-md">
        <div class="container body">
            <div class="main_container">

                <%@include file="../default/menu.jsp" %>
                <!-- page content -->
                <div class="right_col" role="main">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Cadastro de produto<small>(Digite a descri��o do produto para cadastra-lo no sistema)</small></h2> 
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#">Settings 1</a>
                                            </li>
                                            <li><a href="#">Settings 2</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                                                            <%                                                                
                                    if (valid != null && valid.equals("false")) {
                                        out.println("<div class='alert alert-warning alert-dismissible fade in' role='alert'>"
                                                + "<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>�</span>"
                                                + "</button>"
                                                + "<strong>Ocorreu um erro ao inserir o produto</strong> Contate o administrador!"
                                                + "</div>");
                                    }else if(valid != null && valid.equals("true")){
                                        out.println("<div class='alert alert-success alert-dismissible fade in' role='alert'>"
                                                + "<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>�</span>"
                                                + "</button>"
                                                + "<strong>Produto Inserido com sucesso</strong>"
                                                + "</div>");
                                    }
                                %>   
                            <div class="x_content">
                                <br>
                                <form id="demo-form2" action="..\ProdutoServlet" data-parsley-validate="" class="form-horizontal form-label-left" >

                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Descricao:<span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" name="descricao" required="required" class="form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>
                                    <div class="ln_solid"></div>
                                    <div class="form-group">
                                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                            <button class="btn btn-primary" type="button">Cancel</button>
                                            <button class="btn btn-primary" type="reset">Reset</button>
                                            <button type="submit" class="btn btn-success">Submit</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /page content -->
                <%@include file="../default/footer.jsp" %>
            </div>
        </div>
    </body>

    <!-- jQuery -->
    <script src="../vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="../build/js/custom.min.js"></script>

</html>