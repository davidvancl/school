<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes" />
    <xsl:key name="loc" match="//synopsis/act/scene" use="location"/>

    <xsl:template match="synopsis">
        <locations>
            <xsl:for-each select="//synopsis/act/scene[generate-id(.) = generate-id(key('loc',location))]">
                <xsl:sort select="location"/>
                <location>
                    <location_name><xsl:value-of select="location"/></location_name>
                    <xsl:for-each select="key('loc',location)">
                        <location_use>
                            <act_number><xsl:value-of select="../act_number"/></act_number>
                            <scene_number><xsl:value-of select="scene_number"/></scene_number>
                        </location_use>
                    </xsl:for-each>
                </location>
            </xsl:for-each>
        </locations>
    </xsl:template>
</xsl:stylesheet>