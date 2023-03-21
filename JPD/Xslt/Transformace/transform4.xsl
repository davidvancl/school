<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>

    <xsl:template match="obrazek">
        <html>
            <head>
                <title>Tabulka bodů</title>
            </head>
            <body>
                <table>
                    <xsl:apply-templates select="bod">
                        <xsl:sort select="x" data-type="number"/>
                        <xsl:sort select="y" data-type="number"/>
                    </xsl:apply-templates>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="bod">
        <xsl:if test="x >= 0 and y >= 0">
            <xsl:variable name="id">
                <xsl:value-of select="@id"/>
            </xsl:variable>
            <tr>
                <td><xsl:value-of select="$id"/></td>
                <td><xsl:value-of select="x"/></td>
                <td><xsl:value-of select="y"/></td>
                <td>
                    <xsl:for-each select="//obrazek/usecka">
                        <xsl:choose>
                            <xsl:when test="$id = bod[1]">
                                <xsl:value-of select="bod[2]"/>
                                <xsl:text> </xsl:text>
                            </xsl:when>
                            <xsl:when test="$id = bod[2]">
                                <xsl:value-of select="bod[1]"/>
                                <xsl:text> </xsl:text>
                            </xsl:when>
                        </xsl:choose>
                    </xsl:for-each>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>

<!--<xsl:key name="bodik" match="//obrazek/usecka/bod" use="text()" />-->
<!--<table>-->
<!--<xsl:for-each select="//obrazek/usecka/bod[generate-id(.) = generate-id(key('bodik',text()))]">-->
<!--    <xsl:sort select="text()" />-->
<!--    <xsl:variable name="value"><xsl:value-of select="text()"/></xsl:variable>-->

<!--    <xsl:if test="//obrazek/bod[@id = $value]/x >= 0 and //obrazek/bod[@id = $value]/y >= 0">-->
<!--        <tr>-->
<!--            <td><xsl:value-of select="$value"/></td>-->
<!--            <td><xsl:value-of select="//obrazek/bod[@id = $value]/x"/></td>-->
<!--            <td><xsl:value-of select="//obrazek/bod[@id = $value]/y"/></td>-->
<!--        </tr>-->
<!--    </xsl:if>-->
<!--</xsl:for-each>-->
<!--</table>-->