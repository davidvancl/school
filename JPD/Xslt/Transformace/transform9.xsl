<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>

    <xsl:template match="/">
        <html lang="cs">
            <head>
                <title><xsl:text>Seznam publikací</xsl:text></title>
                <meta charset="UTF-8"/>
            </head>
            <body>
                <xsl:apply-templates select="publikace"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="publikace">
        <table>
            <xsl:apply-templates select="polozka">
                <xsl:sort select="autor"/>
            </xsl:apply-templates>
        </table>
    </xsl:template>

    <xsl:template match="polozka">
        <xsl:variable name="invar">
            <xsl:value-of select="in"/>
        </xsl:variable>
        <tr>
            <td><xsl:value-of select="position()"/></td>
            <td><xsl:value-of select="@id"/></td>
            <td><xsl:value-of select="autor"/></td>
            <td>
                <xsl:value-of select="nazev"/>
                <xsl:if test="@typ = 'clanek'">
                    <xsl:text> (</xsl:text>
                    <xsl:value-of select="//polozka[@id = $invar]/nazev"/>
                    <xsl:text>)</xsl:text>
                </xsl:if>
            </td>
        </tr>
    </xsl:template>
</xsl:stylesheet>