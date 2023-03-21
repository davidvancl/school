<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>

    <xsl:template match="/">
        <html lang="cs">
            <head>
                <title><xsl:text>Seznam grafických prvků</xsl:text></title>
            </head>
            <body>
                <table>
                    <xsl:apply-templates select="//grprvek">
                        <xsl:sort select="pozice/x" data-type="number"/>
                        <xsl:sort select="pozice/y" data-type="number"/>
                    </xsl:apply-templates>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="//grprvek">
        <xsl:variable name="uid">
            <xsl:value-of select="@id"/>
        </xsl:variable>
        <tr>
            <td><xsl:value-of select="@id"/></td>
            <td><xsl:value-of select="@typ"/></td>
            <td><xsl:text>x=</xsl:text><xsl:value-of select="pozice/x"/></td>
            <td><xsl:text>y=</xsl:text><xsl:value-of select="pozice/y"/></td>
            <xsl:if test="name(parent::node()) = 'skupina'">
                <td>
                    <xsl:value-of select="parent::node()/@id"/>
                    <xsl:text> (</xsl:text>
                    <xsl:for-each select="parent::node()/grprvek[@id != $uid]">
                        <xsl:sort select="@id"/>
                        <xsl:value-of select="@id"/>
                        <xsl:if test="position() != last()">
                            <xsl:text>,</xsl:text>
                        </xsl:if>
                    </xsl:for-each>
                    <xsl:text>)</xsl:text>
                </td>
            </xsl:if>
        </tr>
    </xsl:template>
</xsl:stylesheet>